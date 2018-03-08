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
	private static String firstNumber = "0";// ��һ�������ֵ
	private static String secondNumber = "0";// �ڶ��������ֵ
	private static String result = "0";// ��ʾ����Ľ��
	private static int flg = 0;// ������ۼ�һ��
	public Counts take = null;

	private int[] btidFCount = { R.id.txtdivide, R.id.txtx, R.id.txtmin,
			R.id.txtadd };// �����ˡ�������
	private Button[] btFCount = new Button[btidFCount.length];

	private int[] btidNum = { R.id.txt0, R.id.txt1, R.id.txt2, R.id.txt3,
			R.id.txt4, R.id.txt5, R.id.txt6, R.id.txt7, R.id.txt8, R.id.txt9,
			R.id.txtspl };// 0��1��2��3��4��5��6��7��8��9��С����
	private Button[] buttons = new Button[btidNum.length];

	private int[] btidFour = { R.id.chars, R.id.charx, R.id.txtpercent,
			R.id.txtsqrt };// �����š����������ࡢ����
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

		Button mc = (Button) findViewById(R.id.MCBtn);// MC,����洢���е���ֵ
		mc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				result = "0";
				print.setText(result);
				flg = 0;
			}
		});

		Button mr = (Button) findViewById(R.id.MRBtn);// MR,�����ڴ洢���е�����ʾ�ڼ���������ʾ����
		mr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				flg = 1;
				print.setText(result);
			}
		});

		Button ms = (Button) findViewById(R.id.MSBtn);// MS,����ʾ�����ֵ���ڴ洢���С�����洢��������ֵ������ʾM��־
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

		Button madd = (Button) findViewById(R.id.MAddBtn);// M+,����ʾ�������洢���е�����Ӳ����д洢
		madd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// flg = flg + Integer.parseInt(result);
				// print.setText(flg);
			}
		});

		Button mj = (Button) findViewById(R.id.MJBtn);// M-,����ʾ�������洢���е�����������д洢
		mj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// flg = Integer.parseInt(result) - flg;
				// print.setText(flg);
			}
		});
		Button eq = (Button) findViewById(R.id.txteq);// �Ⱥţ���ʾ���
		eq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flg == 0) {
					secondNumber = print.getText().toString();
					if (take == Counts.DIVIDE && secondNumber.equals("0")) {
						print.setText("��������Ϊ0");
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

		Button back = (Button) findViewById(R.id.back);// �����һ��
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

		Button clear1 = (Button) findViewById(R.id.clear1);// ����ϴμ�����
		clear1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				result = "0";
				firstNumber = secondNumber = result;
				print.setText(result);
				flg = 0;
			}
		});

		Button clear2 = (Button) findViewById(R.id.clear2);// ������м�����
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

	// �� EditText��ֵ(��̫���)
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
			// matches() �������������ʽ�����Խ�������������ƥ��
			result = s ? (result + txt) : result;
			print.setText(result);
		}
	}

	// ������������
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
			case R.id.chars:// ������
				result = "-" + result;
				break;
			case R.id.charx:// ����:BigDecimal.valueOf()�� double ת��Ϊ BigDecimal��
				if (!result.equals("0"))
					result = BigDecimal
							.valueOf(1)
							.divide(new BigDecimal(result), 12,
									BigDecimal.ROUND_UP).stripTrailingZeros()
							.toString();
				break;
			case R.id.txtpercent:// ����
				result = new BigDecimal(result)
						.divide(BigDecimal.valueOf(100), 12,
								BigDecimal.ROUND_UP).stripTrailingZeros()
						.toString();
				break;
			case R.id.txtsqrt:// ��ƽ��:Math.sqrt()������ȷ����� double ֵ����ƽ������
				Double numss = Math.sqrt(new BigDecimal(result).doubleValue());
				int stratindex = numss.toString().contains(".") ? numss
						.toString().indexOf(".") : 0;
				result = numss.toString().length() > 13 ? numss.toString()
						.substring(0, 12 + stratindex) : numss.toString();
			}// contains():���ҽ������ַ�������ָ���� char ֵ����ʱ������ true��
				// indexOf():����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ���������
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
