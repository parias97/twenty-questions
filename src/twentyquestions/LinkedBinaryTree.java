package twentyquestions;

import java.io.*;
import java.util.Scanner;

public class LinkedBinaryTree<E> {
    // This LinkedStack will store the path the user has taken
    private LinkedStack path;
    private Scanner scan;
    private QNode root;

    public LinkedBinaryTree() {
        scan      = new Scanner(System.in);
        root = null;
    }

    // This method will read in questions and answers from the given file to create the binary tree
    public void readQuestions(Scanner file) {
        root = readQuestionsHelper(file, root);
    }

    /* This method is a helper for read questions will create the binary tree from data in a file.
     *  As parameters this method takes the file it's going to open to extract data, and the root node.
     * The file will be checked to make sure there is data in the file. If the root is null,
     * a QNode is created with the first two lines of the file.
     *
     * The type of node is represented by the isAnswer field. If it's assigned true, the node
     * is a leaf, otherwise it's an interior node.
     */
    private QNode readQuestionsHelper(Scanner file, QNode root) {
        if (file.hasNextLine()) {
            // If root is null, create the root with the data in the file.
            if (root == null) {
                root = new QNode(file.nextLine(), file.nextLine());
            }
            // If the root is a leaf, return itself.
            else if (root.isAnswer.equals("true")) {
                return root;
            }

            // Read questions and answers from the file and create the tree
            root.left = (readQuestionsHelper(file, new QNode(file.nextLine(), file.nextLine())));
            root.right = (readQuestionsHelper(file, new QNode(file.nextLine(), file.nextLine())));
        }

        return root;
    }

    public void askQuestions() {
        askQuestionsHelper(root);
    }


    /*
     * This method is a helper method for askQuestions. If the root node doesn't have any children,
     * ask the question in the root node, and prompt user for input. If the user enters "y" then the
     * computer guessed correctly, otherwise the player is asked what they were thinking about and prompted to input
     * a question that differentiates the computers guess with theirs.
     */
    private QNode askQuestionsHelper(QNode root) {
        QNode temp;
        String questionInput;
        String newQuestion;
        String answerInput;
        path = new LinkedStack();

        // If it's a leaf node check for a guess
        if (root.left == null && root.right == null) {
            // If the users input is invalid, keep asking the same question
            do
            {
                System.out.println(root.value() + "(y/n)");
                questionInput = scan.nextLine();
            }while(!questionInput.equalsIgnoreCase("Y") && !questionInput.equalsIgnoreCase("N"));

            // If the users input is yes, then the computer wins
            if (questionInput.equalsIgnoreCase("y")) {
                System.out.println("\n" + "Gotcha! I win!");
            }
            /* If the users input is no, then prompt the player to input what
             * they were thinking about, and a question to help the computer get smarter
             */
            else if (questionInput.equalsIgnoreCase("n")) {
                System.out.println("\n" + "It seems like you're smarter than me. Please teach me!");
                System.out.println("What were you thinking about?");
                answerInput = scan.nextLine();

                answerInput = "Is it a " + answerInput + "?";

                System.out.println("What yes/no question will help me differentiate your answer with my answer?");
                newQuestion = scan.nextLine();

                // Swap the current QNode, which is pointed to by root, with the new QNode containing the question.
                temp = root;
                root = new QNode(false, newQuestion);
                // Assign its left child the answer to the question and the right child the previous QNode
                root.left = new QNode(true, answerInput);
                root.right = temp;
            }
        }
        // If it's an interior node, ask the question in the node.
        else if (root.left() != null && root.right() != null) {
            do
            {
                System.out.println(root.value() + "(y/n)");
                questionInput = scan.nextLine();
            }while(!questionInput.equalsIgnoreCase("Y") && !questionInput.equalsIgnoreCase("N"));

            // If the users input is yes, traverse through the left side of the current root node
            if (questionInput.equalsIgnoreCase("y")) {
                root.left = askQuestionsHelper(root.left);
                // If the users input is no, traverse through the right side of the current node
            } else if (questionInput.equalsIgnoreCase("n")) {
                root.right = askQuestionsHelper(root.right);
            }
        }

        path.push(root.value());
        return root;
    }
        // This method writes the current binary tree into a file.
        public void writeToFile (PrintStream out)
        {
            writeToFileHelper(out, root);
        }

        /* This method is a helper for writeToFile. This method will
         * write the current binary tree into the file.
         */
        private void writeToFileHelper (PrintStream out, QNode root)
        {
            if (root == null) {
                return;
            }

            // Write to the file the nodes isAnswer and data fields
            out.println(root.isAnswer);
            out.println(root.data);

            /* Traverse the left side of the tree, writing all of the nodes data into to the file.
             * Then traverse, the right side.
             */
            writeToFileHelper(out, root.left);
            writeToFileHelper(out, root.right);
        }

        // This method will print the binary tree
        public void print ()
        {
            System.out.print("[");
            printHelper(path);
            System.out.println("]");
        }

        // This method is a helper for the print method.
        private void printHelper(LinkedStack path)
        {
            // Print out the data in the stack by peeking then popping
            while(path.peek() != null)
            {
                if(path.size() == 1)
                {
                    System.out.print(path.peek());
                    path.pop();
                }
                else {
                    System.out.print(path.peek() + " -> ");
                    path.pop();
                }
            }
        }
}
