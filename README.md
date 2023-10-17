<details>
  <summary> CS211 Midterm Group Project 1 📘 </summary>
  | Author: Leonhard Leung <br>
  | Course: CS211 <br>
  | Class Code: 9342
</details>

## Description
This project is a part of our Data Structures course for the midterms. The project requires us to implement the Stack data structure in converting infix expressions to prefix and postfix expressions and in evaluating postfix expressions.

## Project Structure
- `src/` contains the source codes.
- `src/app` contains the application classes.
- `src/gui` contains the gui classes.
- `src/stack` contains the classes in implementing the Stack data structure.
- `src/util` contains the classes for converting and evaluating expressions.

## Usage
If you want to run the full program, follow these steps:
1. Go to the ExpressionConverterAndEvaluatorApp.java class located in the app package.
2. Run the stated class.

If you only want to test the program, follow these steps:
1. Go to the Test.java class located in the app package.
2. In the run method, there comments that separate each operations. Simply change the expressions to get different results.

## Task Checklist
- [ ] `ExpressionConverterAndEvaluatorApp.java` : Executable class that implements the GUI
- [x] `Test.java` : Executable class for testing the correctness of the program
- [ ] `Frame.java` : Class containing the GUI structure
- [x] `Node.java` : Class that holds the template of a Node
- [x] `Stack.java` : Class that implements the Stack data structure
- [x] `Stackable.java` : Interface that contains the operations of a Stack
- [x] `StackUnderflowException.java` : Class that contains the exception for the Stack class
- [x] `EvaluatePostfixExpression.java` : Class that handles the evaluation of postfix expressions
- [x] `EvaluatePrefixExpression.java` : Class that handles the evaluation of prefix expressions
- [x] `InfixToPostfixConverter.java` : Class that handles the conversion of infix to postfix expressions
- [x] `InfixToPrefixConverter.java` : Class that handles the conversion of infix to prefix expressions
