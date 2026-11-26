public class HelloJenkins {
    public static void main(String[] args) {
        if (args.length == 1 && "--test".equals(args[0])) {
            String message = greeting("Tester", "QA");
            if (!"Hello, Tester! Environment: QA".equals(message)) {
                throw new AssertionError("Smoke test failed: " + message);
            }
            System.out.println("PASS: greeting smoke test");
            return;
        }

        String user = args.length > 0 ? args[0] : "Jenkins Learner";
        String environment = args.length > 1 ? args[1] : "DEV";
        System.out.println(greeting(user, environment));
        System.out.println("Parameterized Jenkins pipeline executed successfully.");
    }

    static String greeting(String user, String environment) {
        return "Hello, " + user + "! Environment: " + environment;
    }
}
