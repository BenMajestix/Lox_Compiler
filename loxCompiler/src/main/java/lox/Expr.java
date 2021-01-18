/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lox;

/**
 *
 * @author benbartel
 */
abstract class Expr { 
    static class Binary extends Expr {
        Binary(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }

}
