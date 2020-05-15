/* Peter Arias
 *  CIS210 - T10
 *  Project 4 - Twenty Questions
 *
 *  This program asks the player 20 Yes or No questions attempting to find out what they are thinking of.
 *  Each question, cuts the possible answers in half to help deduce the answer. The player can continue to play
 *  or end the questionnaire. If the player ends it, the questions and answers are saved into a file.
 */

package twentyquestions;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;

public class twentyquestions
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Create the twenty questions binary tree
        LinkedBinaryTree twentyQuestions = new LinkedBinaryTree();
        Scanner scan                     = new Scanner(System.in);
        // Assign the file name to file
        File file                        = new File("questions_answers.txt");
        String userInput;

        System.out.println("Lets play 20 questions!");
        // Create the binary tree by reading in data from the file
        twentyQuestions.readQuestions(new Scanner(file));

        // Keep playing until the user inputs to stop
        do
        {
            System.out.println("Think of a character, place or thing and I'll try to guess it." + '\n');
            // Traverse the binary tree and ask the question in each node or guess the answer
            twentyQuestions.askQuestions();

            // Keep prompting until the user inputs valid data
            do
            {
                System.out.print("Path: ");
                // Print the path the user has taken
                twentyQuestions.print();
                System.out.println('\n' + "Would you like to continue playing? (y/n)");
                userInput = scan.nextLine();
            }while(!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N"));
        }while(!userInput.equalsIgnoreCase("N"));

        // Write the new binary tree into the file in pre-order
        twentyQuestions.writeToFile(new PrintStream(file));
    }
}
