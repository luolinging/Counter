package com.example.counter;

import java.math.BigDecimal;
//enum关键字是：枚举类型，枚举类型的构造方法都是私有的
//枚举类型每一个值都是public, static and final的.也就是说，
//这些值是唯一的而且一旦定义了是不能被重写或修改.而且尽管在枚举类型每一个值声明时没有出现static关键字，
//实际上值都是静态的, 而且我们不能在值前面加上static, public,final 修饰符,否则就会出现错误

public enum Counts {
	ADD, MINUS, MULTIPLY, DIVIDE;
	public String Values(String num1, String num2) {
		BigDecimal number1 = new BigDecimal(num1);
		BigDecimal number2 = new BigDecimal(num2);
		BigDecimal number = BigDecimal.valueOf(0);
		switch (this) {
		case ADD:
			number = number1.add(number2);
			// add():加法, 返回一个 BigDecimal，其值为 (this + augend)，其标度为
			// max(this.scale(),augend.scale())。
			break;
		case MINUS:
			number = number1.subtract(number2);
			// subtract:减法,返回一个 BigDecimal，其值为 (this - subtrahend)，其标度为
			// max(this.scale(), subtrahend.scale())。
			break;
		case MULTIPLY:
			number = number1.multiply(number2);
			// multiply:乘法,返回一个 BigDecimal，其值为 (this × multiplicand)，其标度为
			// (this.scale() + multiplicand.scale())。
			break;
		case DIVIDE:
			number = number1.divide(number2, 12, BigDecimal.ROUND_UP);
			// divide:除法,返回一个 BigDecimal，其值为 (this / divisor)，其首选标度为
			// (this.scale() - divisor.scale())；如果无法表示准确的商值（因为它有无穷的十进制扩展），则抛出
			// ArithmeticException。
			break;

		}
		return number.stripTrailingZeros().toString();
		//stripTrailingZeros()返回数值上等于此小数，但从该表示形式移除所有尾部零的 BigDecimal。

	}
}
