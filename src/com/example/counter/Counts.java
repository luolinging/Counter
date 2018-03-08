package com.example.counter;

import java.math.BigDecimal;
//enum�ؼ����ǣ�ö�����ͣ�ö�����͵Ĺ��췽������˽�е�
//ö������ÿһ��ֵ����public, static and final��.Ҳ����˵��
//��Щֵ��Ψһ�Ķ���һ���������ǲ��ܱ���д���޸�.���Ҿ�����ö������ÿһ��ֵ����ʱû�г���static�ؼ��֣�
//ʵ����ֵ���Ǿ�̬��, �������ǲ�����ֵǰ�����static, public,final ���η�,����ͻ���ִ���

public enum Counts {
	ADD, MINUS, MULTIPLY, DIVIDE;
	public String Values(String num1, String num2) {
		BigDecimal number1 = new BigDecimal(num1);
		BigDecimal number2 = new BigDecimal(num2);
		BigDecimal number = BigDecimal.valueOf(0);
		switch (this) {
		case ADD:
			number = number1.add(number2);
			// add():�ӷ�, ����һ�� BigDecimal����ֵΪ (this + augend)������Ϊ
			// max(this.scale(),augend.scale())��
			break;
		case MINUS:
			number = number1.subtract(number2);
			// subtract:����,����һ�� BigDecimal����ֵΪ (this - subtrahend)������Ϊ
			// max(this.scale(), subtrahend.scale())��
			break;
		case MULTIPLY:
			number = number1.multiply(number2);
			// multiply:�˷�,����һ�� BigDecimal����ֵΪ (this �� multiplicand)������Ϊ
			// (this.scale() + multiplicand.scale())��
			break;
		case DIVIDE:
			number = number1.divide(number2, 12, BigDecimal.ROUND_UP);
			// divide:����,����һ�� BigDecimal����ֵΪ (this / divisor)������ѡ���Ϊ
			// (this.scale() - divisor.scale())������޷���ʾ׼ȷ����ֵ����Ϊ���������ʮ������չ�������׳�
			// ArithmeticException��
			break;

		}
		return number.stripTrailingZeros().toString();
		//stripTrailingZeros()������ֵ�ϵ��ڴ�С�������Ӹñ�ʾ��ʽ�Ƴ�����β����� BigDecimal��

	}
}
