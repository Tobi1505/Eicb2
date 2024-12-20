/*******************************************************************************
 * Copyright (c) 2016-2019 Embedded Systems and Applications Group
 * Department of Computer Science, Technische Universitaet Darmstadt,
 * Hochschulstr. 10, 64289 Darmstadt, Germany.
 * <p>
 * All rights reserved.
 * <p>
 * This software is provided free for educational use only.
 * It may not be used for commercial purposes without the
 * prior written permission of the authors.
 ******************************************************************************/
package mavlc.context_analysis;

import mavlc.syntax.statement.Declaration;

/*
 * EiCB group number: 103
 * Names and matriculation numbers of all group members:
 * Eric Schwerdtfeger 2862026
 * Tobias Schneider 2562287
 * Felix Meißner 2743307
 */

/**
 * A table for identifiers used inside a function.
 */
public class IdentificationTable {

	protected Scope currentScope=null;
	/**
	 * Declares the given identifier in the current scope.
	 *
	 * @param name the identifier to declare
	 * @param declaration the reference to the identifier's declaration site
	 */
	public void addIdentifier(String name, Declaration declaration) {
		if (currentScope!=null){
			currentScope.addIdentifier(name, declaration);
		}
		else throw new IllegalStateException("no open scope");
	}
	
	/**
	 * Looks up the innermost declaration of the given identifier.
	 *
	 * @param name the identifier to look up
	 * @return the identifier's innermost declaration site
	 */
	public Declaration getDeclaration(String name) {
		if (currentScope!=null)return currentScope.getDeclaration(name);
		else throw new IllegalStateException("no open scope");
	}
	
	/**
	 * Opens a new scope.
	 */
	public void openNewScope() {
		currentScope=new Scope(currentScope);
	}
	
	/**
	 * Closes the current scope.
	 */
	public void closeCurrentScope() {
		if (currentScope!=null) currentScope=currentScope.parentScope;
		else throw new IllegalStateException("No scope to close");
	}
}
