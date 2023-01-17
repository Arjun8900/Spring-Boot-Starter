package com.linux.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CmdUtils {

    public static String runCommand(String command) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        String[] args = new String[]{"cmd", "/c", command};
        ProcessBuilder processBuilder = new ProcessBuilder(args);

        // Set working directory
        String joinedCommand = String.join(" ", processBuilder.command().toArray(new String[0]));
        log.info(String.format("Command = %s", command));

        Process proc = processBuilder.start();

        // Read the output from the command
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String s = null;
        while ((s = stdOutput.readLine()) != null) {
            sb.append(s).append("\n");
            log.info(s);
        }

        // Read any errors from the attempted command
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        while ((s = stdError.readLine()) != null) {
            log.error(s);
        }
        stdError.close();

        int exitValue = proc.waitFor();
        if (exitValue != 0) {
            log.info(String.format("ERROR: RunCMD Exit value is = %s, command = %s %n%n", exitValue, command));
            throw new RuntimeException("ERROR: In running Shell script");
        }

        proc.destroy();
        return sb.toString();

    }

    public static List<String> runCommandWithList(String command) {
        List<String> result = new ArrayList<>();
        try {
            String[] args = new String[]{"cmd", "/c", command};
            ProcessBuilder processBuilder = new ProcessBuilder(args);

            // Set working directory
            String joinedCommand = String.join(" ", processBuilder.command().toArray(new String[0]));
            log.info(String.format("Command = %s", joinedCommand));

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
                log.error(s);
                result.add(s);
            }
            stdError.close();

            int exitValue = proc.waitFor();
            if (exitValue != 0) {
                log.error(String.format("RunCMD Exit value is = %s, command = %s %n%n", exitValue, command));
            }

            proc.destroy();
        } catch (Exception ex) {
            log.error("ERRORS: In running Shell script = " + command);
        }
        return result;
    }

    public static String runCommand(String directory, String command) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        try {
            String[] args = new String[]{"cmd", "/c", command};
            ProcessBuilder processBuilder = new ProcessBuilder(args);

            // Set working directory
            processBuilder.directory(new File(directory));
            String joinedCommand = String.join(" ", processBuilder.command().toArray(new String[0]));
            log.info(String.format("Location = %s, Command = %s", directory, command));

            Process proc = processBuilder.start();

            // Read the output from the command
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = null;
            while ((s = stdOutput.readLine()) != null) {
                log.info(s);
                sb.append(s).append("\n");
            }

            // Read any errors from the attempted command
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while ((s = stdError.readLine()) != null) {
                log.error(s);
                sb.append(s).append("\n");
            }
            stdError.close();

            int exitValue = proc.waitFor();
            if (exitValue != 0) {
                log.error(String.format("ERROR: RunCMD Exit value is = %s, command = %s %n%n", exitValue, command));
            }

            proc.destroy();
        } catch (Exception ex) {
            log.error("ERRORS: In running Shell script = " + command);
        }
        return sb.toString();

    }

    public static String runShell(String command) {

        try {
            // Run "netsh" Windows command
            Process process = Runtime.getRuntime().exec(command);

            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Read command standard output
            String s;
            System.out.println("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Read command errors
            System.out.println("Standard error: ");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return "";
    }

}
