package com.linux.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BashUtils {

    public static List<String> runShell(String cmd) {
        List<String> result = new ArrayList<>();
        try {
            String[] args = new String[]{"/bin/sh", "-c", cmd};
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            log.info(String.join(" ", processBuilder.command().toArray(new String[0])));

            Process proc = processBuilder.start();

            // Read the output from the command
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = null;
            while ((s = stdOutput.readLine()) != null) {
                result.add(s);
                log.info(s);
            }

            // Read any errors from the attempted command
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((s = stdError.readLine()) != null) {
                result.add(s);
                log.error(s);
            }
            stdError.close();

            int exitValue = proc.waitFor();
            if (exitValue != 0) {
                log.error(String.format("RunCMD Exit value is = %s, command = %s %n%n", exitValue, cmd));
            }

            proc.destroy();
        } catch (Exception ex) {
            log.error("ERRORS: In running Shell script = " + cmd);
        }
        return result;
    }

    public static int runShell(String fileName, String factTables) {
        try {
            String[] args = new String[]{"/bin/sh", "-c", fileName + " " + factTables};
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            System.out.println(String.join(" ", processBuilder.command().toArray(new String[0])));

            Process proc = processBuilder.start();

            // Read the output from the command
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = null;
            while ((s = stdOutput.readLine()) != null) {
                System.out.println(s);
            }

            // Read any errors from the attempted command
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            stdError.close();

            int exitValue = proc.waitFor();
            System.out.println("RunShell Exit value is = " + exitValue);

            proc.destroy();
            return exitValue;
        } catch (Exception ex) {
            System.out.println("ERRORS: In running Shell script");
        }
        return 255;
    }
}
