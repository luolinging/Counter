package com.example.counter;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends Activity {

	private EditText print;
	private static String firstNumber = "0";// 第一次输入的值
	private static String secondNumber = "0";// 第二次输入的值
	private static String result = "0";// 显示计算的结果
	private static int flg = 0;// 将结果累加一次
	public Counts take = null;

	private int[] btidFCount = { R.id.txtdivide, R.id.txtx, R.id.txtmin,
			R.id.txtadd };// 除、乘、减、加
	private Button[] btFCount = new Button[btidFCount.length];

	private int[] btidNum = { R.id.txt0, R.id.txt1, R.id.txt2, R.id.txt3,
			R.id.txt4, R.id.txt5, R.id.txt6, R.id.txt7, R.id.txt8, R.id.txt9,
			R.id.txtspl };// 0、1、2、3、4、5、6、7、8、9、小数点
	private Button[] buttons = new Button[btidNum.length];

	private int[] btidFour = { R.id.chars, R.id.charx, R.id.txtpercent,
			R.id.txtsqrt };// 正负号、倒数、求余、根号
	private Button[] btFour = new Button[btidFour.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		print = (EditText) findViewById(R.id.print);
		print.setText("0");
		print.setEnabled(false);

		GetNumber get = new GetNumber();
		for (int i = 0; i < btidNum.length; i++) {
			buttons[i] = (Button) findViewById(btidNum[i]);
			buttons[i].setOnClickListener(get);
		}
		Compute cm = new Compute();
		for (int i = 0; i < btidFCount.length; i++) {
			btFCount[i] = (Button) findViewById(btidFCount[i]);
			btFCount[i].setOnClickListener(cm);
		}

		Button mc = (Button) findViewById(R.id.MCBtn);// MC,清除存储器中的数值
		mc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				result = "0";
				print.setText(result);
				flg = 0;
			}
		});

		Button mr = (Button) findViewById(R.id.MRBtn);// MR,将存于存储器中的数显示在计算器的显示框上
		mr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flg = 1;
				print.setText(result);
			}
		});

		Button ms = (Button) findViewById(R.id.MSBtn);// MS,将显示框的数值存于存储器中。如果存储器中有数值将会显示M标志
		ms.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flg != 0) {
					print.setText("M");
				} else {
					print.setText(result);
				}
			}
		});

		Button madd = (Button) findViewById(R.id.MAddBtn);// M+,将显示框的数与存储器中的数相加并进行存储
		madd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// flg = flg + Integer.parseInt(result);
				// print.setText(flg);
			}
		});

		Button mj = (Button) findViewById(R.id.MJBtn);// M-,将显示框的数与存储器中的数相减并进行存储
		mj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// flg = Integer.parseInt(result) - flg;
				// print.setText(flg);
			}
		});
		Button eq = (Button) findViewById(R.id.txteq);// 等号，显示结果
		eq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flg == 0) {
					secondNumber = print.getText().toString();
					if (take == Counts.DIVIDE && secondNumber.equals("0")) {
						print.setText("除数不能为0");
					} else {
						result = take.Values(firstNumber, secondNumber);
						firstNumber = result;
						secondNumber = "0";
						print.setText(result);
						flg = 1;
					}
				}
			}
		});

		Button back = (Button) findViewById(R.id.back);// 清除上一个
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (result.length() > 1) {
					result = result.substring(0, result.length() - 1);
				} else {
					result = "0";
				}
				print.setText(result);
			}
		});

		Button clear1 = (Button) findViewById(R.id.clear1);// 清空上次计算结果
		clear1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				result = "0";
				firstNumber = secondNumber = result;
				print.setText(result);
				flg = 0;
			}
		});

		Button clear2 = (Button) findViewById(R.id.clear2);// 清空所有计算结果
		clear2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				result = "0";				
				print.setText(result);
				flg = 0;
			}
		});

		for (int i = 0; i < btidFour.length; i++) {
			btFour[i] = (Button) findViewById(btidFour[i]);
			btFour[i].setOnClickListener(new OnTake());
		}
	}

	// 给 EditText赋值(不太理解)
	class GetNumber implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (flg == 1)
				result = "0";
			if (result.equals("0")) {
				print.setText("");
				result = v.getId() == R.id.txtspl ? "0" : "";
			}
			String txt = ((Button) v).getText().toString();
			boolean s = Pattern.matches("-*(\\d+).?(\\d)*", result + txt);
			// matches() 编译给定正则表达式并尝试将给定输入与其匹配
			result = s ? (result + txt) : result;
			print.setText(result);
		}
	}

	// 根据条件计算
	class Compute implements OnClickListener {

		@Override
		public void onClick(View v) {

			firstNumber = print.getText().toString();
			switch (v.getId()) {
			case R.id.txtadd:
				take = Counts.ADD;
				break;
			case R.id.txtmin:
				take = Counts.MINUS;
				break;
			case R.id.txtx:
				take = Counts.MULTIPLY;
				break;
			case R.id.txtdivide:
				take = Counts.DIVIDE;
				break;
			}
			result = "0";
			flg = 0;
		}

	}

	class OnTake implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.chars:// 正负号
				result = "-" + result;
				break;
			case R.id.charx:// 倒数:BigDecimal.valueOf()将 double 转换为 BigDecimal。
				if (!result.equals("0"))
					result = BigDecimal
							.valueOf(1)
							.divide(new BigDecimal(result), 12,
									BigDecimal.ROUND_UP).stripTrailingZeros()
							.toString();
				break;
			case R.id.txtpercent:// 求余
				result = new BigDecimal(result)
						.divide(BigDecimal.valueOf(100), 12,
								BigDecimal.ROUND_UP).stripTrailingZeros()
						.toString();
				break;
			case R.id.txtsqrt:// 开平方:Math.sqrt()返回正确舍入的 double 值的正平方根。
				Double numss = Math.sqrt(new BigDecimal(result).doubleValue());
				int stratindex = numss.toString().contains(".") ? numss
						.toString().indexOf(".") : 0;
				result = numss.toString().length() > 13 ? numss.toString()
						.substring(0, 12 + stratindex) : numss.toString();
			}// contains():当且仅当此字符串包含指定的 char 值序列时，返回 true。
				// indexOf():返回指定子字符串在此字符串中第一次出现处的索引。
			print.setText(result);
			flg = 0;
			result = "0";

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
