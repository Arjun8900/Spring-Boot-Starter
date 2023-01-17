package com.linux.service;

import com.linux.dto.LinuxResponse;
import com.linux.utils.BashUtils;
import com.linux.utils.CmdUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AgentInformation {

    public static String REMOTE_DIR = "root@invarjun0201";

    public static String buildRemoteCommand(String remoteHost, String command) {
        return String.format("ssh %s \"%s\"", remoteHost, command);
    }

    public static String getOsName() {
        String osName = System.getProperty("os.name");
        log.info("OsName = " + osName);
        return osName;
    }

    public List<LinuxResponse> getLatestRemoteDirCmd(String podName, String packageName) {
        String command = "ls -td  /root/Documents/ml/downloads/*" + packageName + "*/package/*" + packageName + "*/cci/plugins/";

        return executedCommandResponse(podName, command)
                .stream().map(res -> LinuxResponse.builder().name(res).build())
                .collect(Collectors.toList());
    }

    public List<LinuxResponse> getAgentStatus(String podName) {
        String command = "cd /root/Documents/" + podName + "/apps/agentcore; ./consoleAgentManager.sh getStatus";

        return executedCommandResponse(podName, command)
                .stream().map(res -> LinuxResponse.builder().name(res).build())
                .collect(Collectors.toList());
    }

    public List<LinuxResponse> shutDownAgent(String podName) {
        String command = "cd /root/Documents/" + podName + "/apps/agentcore; ./infaagent shutdown";

        return executedCommandResponse(podName, command)
                .stream().map(res -> LinuxResponse.builder().name(res).build())
                .collect(Collectors.toList());
    }

    public List<LinuxResponse> startUpAgent(String podName) {
        String command = "cd /root/Documents/" + podName + "/apps/agentcore; ./infaagent startup";

        return executedCommandResponse(podName, command)
                .stream().map(res -> LinuxResponse.builder().name(res).build())
                .collect(Collectors.toList());
    }

    public List<LinuxResponse> restartAgent(String podName) {
        String command = "ssh root@invarjun0201 \"cd /root/Documents/" + podName + "/apps/agentcore; ./re.sh\"";

        return executedCommandResponse(podName, command)
                .stream().map(res -> LinuxResponse.builder().name(res).build())
                .collect(Collectors.toList());
    }

    /**
     * Executes command based on os type
     *
     * @param command Command to execute in deployed machine
     * @return List of response
     */
    private List<String> executedCommandResponse(String podName, String command) {
        List<String> linuxCmdResult = new ArrayList<>();
        try {
            log.info(String.format("Running command = %s", command));

            String osName = getOsName();
            if (osName.toLowerCase().contains("win")) {
                command = buildRemoteCommand(REMOTE_DIR, command);
                linuxCmdResult = CmdUtils.runCommandWithList(command);
            } else {
                linuxCmdResult = BashUtils.runShell(command);
            }
        } catch (Exception e) {
            log.error(String.format("Error for podName = %s, message = %s", podName, e.getMessage()));
            e.getStackTrace();
        }
        return linuxCmdResult;
    }
}
