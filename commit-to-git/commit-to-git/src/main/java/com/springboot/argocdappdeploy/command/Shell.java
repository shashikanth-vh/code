package com.springboot.argocdappdeploy.command;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Shell {

    /** Returns null if it failed for some reason.
     */
    public static ArrayList<String> command(final String cmdline,
                                            final String directory) {
        try {
            Process process =
                    new ProcessBuilder(new String[] {"bash", "-c", cmdline})
                            .redirectErrorStream(true)
                            .directory(new File(directory))
                            .start();

            ArrayList<String> output = new ArrayList<String>();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = null;
            while ( (line = br.readLine()) != null )
                output.add(line);

            //There should really be a timeout here.
            if (0 != process.waitFor())
                return null;

            return output;

        } catch (Exception e) {
            //Warning: doing this is no good in high quality applications.
            //Instead, present appropriate error messages to the user.
            //But it's perfectly fine for prototyping.

            return null;
        }
    }

    public static void executeCommand(String argoToken, String appName, String appRepo, String app) {
        test("which bash");
        test(argoToken);

        /*test("find . -type f -printf '%T@\\\\t%p\\\\n' "
                + "| sort -n | cut -f 2- | "
                + "sed -e 's/ /\\\\\\\\ /g' | xargs ls -halt");*/
        //test("cd /home; ls");
        /*

        argoPass=$(kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d);
        argocd login localhost:30842 --username  admin --insecure --password $argoPass;
        argocd app create guestbook --repo https://github.com/argoproj/argocd-example-apps.git --path guestbook --dest-server https://kubernetes.default.svc --dest-namespace default;
        argocd app set guestbook --sync-policy automated

        */
        String clone_repo = "git clone https://github.com/shashikanth-vh/helm-charts.git";
        test(clone_repo);

        //move values.yaml
        test("mv /bin/values.yaml /helm-charts/example-app/");

        String script = "./bin/git.sh ;";

        test(script + "" + "");

    }

    static void test(String cmdline) {
        ArrayList<String> output = command(cmdline, ".");
        if (null == output)
            System.out.println("\n\n\t\tCommand Execution Failed: " + cmdline);
        else
            for (String line : output)
                System.out.println(line);

    }
}