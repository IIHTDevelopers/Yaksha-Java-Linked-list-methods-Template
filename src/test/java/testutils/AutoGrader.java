package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class AutoGrader {

	// Test if the code correctly implements LinkedList methods
	public boolean testLinkedListMethods(String filePath) throws IOException {
		System.out.println("Starting testLinkedListMethods with file: " + filePath);

		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Flags to check presence and calls of specific methods
		boolean hasMainMethod = false;

		// Check for method declarations
		for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
			String methodName = method.getNameAsString();
			// Check for the presence of the main method
			if (methodName.equals("main")) {
				hasMainMethod = true;
			}
		}

		// Check for method calls in the main method
		boolean calledAddFirst = false;
		boolean calledAddLast = false;
		boolean calledRemoveFirst = false;
		boolean calledRemoveLast = false;
		boolean calledGetFirst = false;
		boolean calledGetLast = false;

		for (MethodCallExpr methodCall : cu.findAll(MethodCallExpr.class)) {
			String methodName = methodCall.getNameAsString();
			if (methodName.equals("addFirst")) {
				calledAddFirst = true;
			} else if (methodName.equals("addLast")) {
				calledAddLast = true;
			} else if (methodName.equals("removeFirst")) {
				calledRemoveFirst = true;
			} else if (methodName.equals("removeLast")) {
				calledRemoveLast = true;
			} else if (methodName.equals("getFirst")) {
				calledGetFirst = true;
			} else if (methodName.equals("getLast")) {
				calledGetLast = true;
			}
		}

		// Log method presence and calls
		System.out.println("Method 'main' is " + (hasMainMethod ? "present" : "NOT present"));
		System.out.println("Method 'addFirst' is " + (calledAddFirst ? "called" : "NOT called"));
		System.out.println("Method 'addLast' is " + (calledAddLast ? "called" : "NOT called"));
		System.out.println("Method 'removeFirst' is " + (calledRemoveFirst ? "called" : "NOT called"));
		System.out.println("Method 'removeLast' is " + (calledRemoveLast ? "called" : "NOT called"));
		System.out.println("Method 'getFirst' is " + (calledGetFirst ? "called" : "NOT called"));
		System.out.println("Method 'getLast' is " + (calledGetLast ? "called" : "NOT called"));

		// Final result
		boolean result = hasMainMethod && calledAddFirst && calledAddLast && calledRemoveFirst && calledRemoveLast
				&& calledGetFirst && calledGetLast;

		System.out.println("Test result: " + result);
		return result;
	}
}
