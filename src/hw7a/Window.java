package hw7a;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Window extends JFrame implements MouseListener {


    final int NUMBERS_X = 10;
    final int NUMBERS_Y = 240;

    final int BTN_WIDTH = 80;
    final int BTN_HEIGHT = 80;

    final int OPERATION_X = NUMBERS_X + BTN_WIDTH * 3;
    final int OPERATION_Y = NUMBERS_Y;

    final int FIELD_WIDTH = 250;
    final int FIELD_HEIGHT = 50;

    final int WIN_X_LEFT = 700;
    final int WIN_Y_TOP = 200;
    final int WIN_WIDTH = BTN_WIDTH * 4 + NUMBERS_X * 3 + 4;
    final int WIN_HEIGHT = 687;


    Button btnPlus = new Button(" + ");
    Button btnMinus = new Button(" - ");
    Button btnMultiplication = new Button(" * ");
    Button btnDivision = new Button(" / ");
    Button btnZero = new Button(" 0 ");
    Button btnDot = new Button(" . ");
    Button btnC = new Button(" C ");
    Button btnCE = new Button("CE");
    Button btnBackSpace = new Button("BackSpace");
    Button btnEqually = new Button("=");

    Button[] btnNumbers = new Button[9];


    Font font = new Font(null, Font.BOLD, 52);
    Font fontBackSpace = new Font(null, Font.BOLD, 26);


    JTextField field0 = new JTextField();
    JTextField field1 = new JTextField();
    StringBuilder sb = new StringBuilder();
    boolean nextClear;


    public Window() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(WIN_X_LEFT, WIN_Y_TOP, WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);

        btnPlus.setFont(font);
        btnMinus.setFont(font);
        btnMultiplication.setFont(font);
        btnDivision.setFont(font);
        btnZero.setFont(font);
        btnDot.setFont(font);
        btnBackSpace.setFont(fontBackSpace);
        btnCE.setFont(font);
        btnC.setFont(font);
        btnEqually.setFont(font);
        setLayout(null);

        for (int i = 0; i <= btnNumbers.length - 1; i++) {
            btnNumbers[i] = new Button(" " + (i + 1) + " ");
            btnNumbers[i].setFont(font);
            int nX = i;
            int nY = 0;
            if (i >= 3 && i <= 5) {
                nX = i - 3;
                nY = BTN_HEIGHT;
            }
            if (i >= 6 && i <= 8) {

                nX = i - 6;
                nY = BTN_HEIGHT * 2;
            }
            btnNumbers[i].setBounds(NUMBERS_X + BTN_WIDTH * nX, NUMBERS_Y + nY, BTN_WIDTH, BTN_HEIGHT);
            btnNumbers[i].addMouseListener(this);
            add(btnNumbers[i]);
        }

        btnPlus.setBounds(OPERATION_X, OPERATION_Y, BTN_WIDTH, BTN_HEIGHT);
        btnMinus.setBounds(OPERATION_X, OPERATION_Y + BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
        btnMultiplication.setBounds(OPERATION_X, OPERATION_Y + BTN_HEIGHT * 2, BTN_WIDTH, BTN_HEIGHT);
        btnDivision.setBounds(OPERATION_X, OPERATION_Y + BTN_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT);
        btnZero.setBounds(NUMBERS_X, NUMBERS_Y + BTN_HEIGHT * 3, BTN_WIDTH * 2, BTN_HEIGHT);
        btnDot.setBounds(NUMBERS_X + BTN_WIDTH * 2, NUMBERS_Y + BTN_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT);
        btnBackSpace.setBounds(NUMBERS_X, NUMBERS_Y - BTN_HEIGHT, BTN_WIDTH * 2, BTN_HEIGHT);
        btnCE.setBounds(NUMBERS_X + BTN_WIDTH * 2, NUMBERS_Y - BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
        btnC.setBounds(NUMBERS_X + BTN_WIDTH * 3, NUMBERS_Y - BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
        btnEqually.setBounds(NUMBERS_X, NUMBERS_Y + BTN_HEIGHT * 4, BTN_WIDTH * 4, BTN_HEIGHT);


        field0.setBounds(NUMBERS_X + BTN_WIDTH * 4 - FIELD_WIDTH, NUMBERS_Y - FIELD_HEIGHT * 4, FIELD_WIDTH, FIELD_HEIGHT);
        field0.setEditable(false);
        field1.setBounds(NUMBERS_X + BTN_WIDTH * 4 - FIELD_WIDTH, NUMBERS_Y - FIELD_HEIGHT * 3, FIELD_WIDTH, FIELD_HEIGHT);
        field1.setEnabled(false);

        btnZero.addMouseListener(this);
        btnC.addMouseListener(this);
        btnBackSpace.addMouseListener(this);
        btnPlus.addMouseListener(this);
        btnMinus.addMouseListener(this);
        btnMultiplication.addMouseListener(this);
        btnDivision.addMouseListener(this);
        btnEqually.addMouseListener(this);
        btnDot.addMouseListener(this);

        add(field0);
        add(field1);
        add(btnPlus);
        add(btnMinus);
        add(btnDivision);
        add(btnMultiplication);
        add(btnZero);
        add(btnDot);
        add(btnBackSpace);
        add(btnCE);
        add(btnC);
        add(btnEqually);

        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (nextClear) {
            field1.setText("");
        }

        Object ee = e.getSource();
        String txt0 = field0.getText();
        String txt1 = field1.getText();
        boolean isChangeSign = false;

        nextClear = false;

        if (txt0.length() > 2) {
            String s;
            sb.setLength(0);
            sb.append(txt0);
            s = sb.substring(txt0.length() - 1, txt0.length()).trim();
            if (s.length() == 1 && (s.equals("*") | s.equals("-") | s.equals("/") | s.equals("+"))) {
                isChangeSign = true;
            }
        }

        if (getSign(ee) != null) {
            String sign = getSign(ee);
            if (isChangeSign && txt1.equals("")) {
                sb.setLength(0);
                sb.append(txt0);
                sb.deleteCharAt(txt0.length() - 1);
                txt0 = sb.toString();
            }
            field0.setText(txt0 + txt1 + sign);
            field1.setText(setEquals(field0.getText()));
            nextClear = true;

        }

        for (int i = 0; i <= btnNumbers.length - 1; i++) {
            if (ee == btnNumbers[i]) {
                field1.setText(txt1 + (i + 1));
                break;
            }
        }

        if (ee == btnZero && txt1.length() > 0) {
            field1.setText(txt1 + "0");
        }

        if (ee == btnEqually) {
            field1.setText(setEquals(field0.getText()));
            field0.setText("");
        }

        if (ee == btnC) {
            field1.setText("");
            field0.setText("");
        }
        if (ee == btnBackSpace && txt1.length() > 0) {
            sb.setLength(0);
            sb.append(txt1);
            sb.deleteCharAt(txt1.length() - 1);
            field1.setText(sb.toString());
        }
        if (ee == btnDot) {
            if (txt1.length() > 0) {
                if (txt1.charAt(txt1.length()-1) != '.') {
                    field1.setText(txt1 + '.');
                }
            } else {
                field1.setText(txt1 + '.');
            }
        }
    }

    public String getSign(Object e) {
        String txt0 = field0.getText();
        String txt1 = field1.getText();
        if (e == btnPlus) {
            return "+";
        }
        if (e == btnMinus) {
            return "-";
        }
        if (e == btnMultiplication) {
            return "*";
        }
        if (e == btnDivision) {
            return "/";
        }
        return null;
    }


    public String setEquals(String inText) {
        StringBuilder sb = new StringBuilder();
        boolean nextPlus = false;
        boolean nextMinus = false;
        boolean nextDiv = false;
        boolean nextMult = false;
        Double sum = 0.0;
        Pattern pattern = Pattern.compile("(\\d|\\.)+|\\+|\\-|\\*|\\/");
        Matcher matcher = pattern.matcher(inText);

        while (matcher.find()) {
            sb.setLength(0);
            sb.append(matcher.group());
            String s = sb.toString();
            if (tryParseDouble(s)) {
                Double newSum = Double.parseDouble(s);
                if (nextPlus) {
                    sum += newSum;
                } else if (nextMinus) {
                    sum -= newSum;
                } else if (nextDiv) {
                    sum /= newSum;
                } else if (nextMult) {
                    sum *= newSum;
                } else {
                    sum = newSum;
                }
            }
            nextPlus = false;
            nextMinus = false;
            nextDiv = false;
            nextMult = false;

            if (s.equals("+")) {
                nextPlus = true;
            } else if (s.equals("-")) {
                nextMinus = true;
            } else if (s.equals("/")) {
                nextDiv = true;
            } else if (s.equals("*")) {
                nextMult = true;
            }
        }

        return sum.toString();
    }


    public boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
