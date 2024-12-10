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

import mavlc.errors.NonConstantExpressionError;
import mavlc.syntax.AstNode;
import mavlc.syntax.AstNodeBaseVisitor;
import mavlc.syntax.expression.*;

/*
 * EiCB group number: 103
 * Names and matriculation numbers of all group members:
 * Eric Schwerdtfeger 2862026
 * Tobias Schneider 225673
 * Felix Meißner 2743307
 */

public class ConstantExpressionEvaluator extends AstNodeBaseVisitor<Integer, Void> {
	@Override
	protected Integer defaultOperation(AstNode node, Void obj) {
		if(node instanceof Expression) {
			throw new NonConstantExpressionError((Expression) node);
		} else {
			throw new RuntimeException("Internal compiler error: should not try to constant-evaluate non-expressions");
		}
	}
	
	@Override
	public Integer visitIntValue(IntValue intValue, Void __) {
		return intValue.value;
	}
	
	// TODO implement (exercise 2.3)
	@Override
	public Integer visitAddition(Addition addition, Void __){
		return addition.leftOperand.accept(this) + addition.rightOperand.accept(this);
	}

	@Override
	public Integer visitSubtraction(Subtraction subtraction, Void __){
		return subtraction.leftOperand.accept(this) - subtraction.rightOperand.accept(this);
	}

	@Override
	public Integer visitMultiplication(Multiplication multiplication, Void __){
		return multiplication.leftOperand.accept(this) * multiplication.rightOperand.accept(this);
	}

	@Override
	public Integer visitDivision(Division division, Void __){
		return division.leftOperand.accept(this) / division.rightOperand.accept(this);
	}

	@Override
	public Integer visitExponentiation(Exponentiation exponentiation, Void __){
		int base = exponentiation.leftOperand.accept(this);
		int exponent = exponentiation.rightOperand.accept(this);
		if (exponent < 0){
			throw new ArithmeticException();
		}
		int power=1;
		for (int i=0; i < exponent; i++){
			power = power*base;
		}
		return power;
	}
	@Override
	public Integer visitUnaryMinus(UnaryMinus unaryMinus, Void __){
		return -unaryMinus.operand.accept(this);
	}
}
