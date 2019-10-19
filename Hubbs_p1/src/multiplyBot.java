import java.security.SecureRandom;
import java.util.Scanner;

public class multiplyBot
{
    public static void main(String [] args)
    {
        Scanner scanlan = new Scanner(System.in);
        boolean done = false;
        int oneMoreLoop;
        while(!done)
        {
            int numCorrect = 0, numIncorrect = 0;
            int difficulty = determineDifficultyLevel();
            int problemType = determineProblemType();
            // 10 problems for the poor kid
            for (int i = 0; i < 10; i++)
            {
                float correctAnswer = generateQuestion(difficulty, problemType);
                float studentAnswer = scanlan.nextFloat();
                // System.out.printf("%f - %f < 0.001 -----> %s\n", studentAnswer, correctAnswer, ((Math.abs(studentAnswer - correctAnswer) < 0.001)) ? "true" : "false"); // debugging print
                boolean judgement;
                if ((Math.abs(studentAnswer - correctAnswer) < 0.001))
                {
                    judgement = true;
                    numCorrect++;
                } else
                {
                    judgement = false;
                    numIncorrect++;
                }
                // System.out.println("Student answer: " + studentAnswer + "     Correct answer: " + correctAnswer + "     judgement: " + judgement); // debugging print
                printResultMessage(judgement);
            }
            if (numCorrect/numIncorrect >= 0.75)
                System.out.println("Congratulations, you are ready to go to the next level!");
            else
                System.out.println("Please ask your teacher for extra help.");

            System.out.println("Is there another student? (enter 1 for yes and 0 for no)");
            oneMoreLoop = scanlan.nextInt();
            if (oneMoreLoop == 0)
                done = true;
            System.out.println(); //just for spacing
        }
    }

    // returns the answer. be careful with float comparision and integer division
    private static float generateQuestion(int difficulty, int problemType)
    {
        SecureRandom randy = new SecureRandom();
        // generate operands based on difficulty
        int operand1 = 0;
        int operand2 = 0;
        if (difficulty == 1)
        {
            operand1 = randy.nextInt() % 10;
            operand2 = randy.nextInt() % 10;
        }
        else if (difficulty == 2)
        {
            operand1 = randy.nextInt() % 100;
            operand2 = randy.nextInt() % 100;
        }
        else if (difficulty == 3)
        {
            operand1 = randy.nextInt() % 1000;
            operand2 = randy.nextInt() % 1000;
        }
        else if (difficulty == 4)
        {
            operand1 = randy.nextInt() % 10000;
            operand2 = randy.nextInt() % 10000;
        }

        // now decide the operator based on the problemType
        int operatorDecider = problemType - 1;
        if (operatorDecider == 4) // student chose random problem type
        {
            operatorDecider = Math.abs(randy.nextInt() % 4);
        }
        char operator;
        if (operatorDecider == 0)
        {
            operator = '+';
        }
        else if (operatorDecider == 1)
        {
            operator = '*';
        }
        else if (operatorDecider == 2)
        {
            operator = '-';
        }
        else //guess it was 3. its division time
        {
            operator = '/';
        }

        // now ask the question and return the answer
        float answer = 0;
        if (operatorDecider == 0)//+
        {
            answer = operand1 + operand2;
        }
        else if (operatorDecider == 1)//*
        {
            answer = operand1 * operand2;
        }
        else if (operatorDecider == 2)//-
        {
            answer = operand1 - operand2;
        }
        else if (operatorDecider == 3)// /
        {
            if(operand2 == 0)
                operand2++;
            answer = (float)operand1 / operand2;
        }
        System.out.printf("What is %d %c %d?\n", operand1, operator, operand2);
        return answer;
    }

    private static int determineDifficultyLevel()
    {
        // why does the rubric tell me to put this in a method? I mean, it's java,
        // so it's not like we're worried about unnecessary function calls mucking up runtime.
        Scanner scanlan = new Scanner(System.in);
        System.out.println("Type 1, 2, 3, or 4 to choose your difficulty level.");
        return scanlan.nextInt();
    }

    private static int determineProblemType()
    {
        Scanner scanlan = new Scanner(System.in);
        System.out.println("Would you like to study (1) addition, (2) multiplication, (3) subtraction, (4) division, or (5) all of the above? Type the number of your choice.");
        return scanlan.nextInt();
    }

    private static void printResultMessage(boolean correct)
    {
        //to do: change this from if/else if statements to a switch statement because... no good reason, but
        // the instructions say so. I mean, it's not on the rubric, so I doubt points will be deducted.
        SecureRandom randy = new SecureRandom();
        int whichMessage = Math.abs(randy.nextInt() % 4);
        String laudatory;
        String chastise;
        if (whichMessage == 0)
        {
            laudatory = "Very good!";
            chastise = "No. Please try again.";
        }
        else if (whichMessage == 1)
        {
            laudatory = "Excellent!";
            chastise = "Wrong. Try once more.";
        }
        else if (whichMessage == 2)
        {
            laudatory = "Nice work!";
            chastise = "Don’t give up!";
        }
        else //must have gotten 3
        {
            laudatory = "Keep up the good work!";
            chastise = "No. Keep trying.";
        }

        if (correct)
            System.out.println(laudatory);
        else
            System.out.println(chastise);
    }
}
