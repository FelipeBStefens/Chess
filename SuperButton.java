package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperButton extends JButton implements ActionListener{

    private int LENGTHBUTTON = 96;
    private boolean select = true;
    private boolean ableMove = false;


    public SuperButton(int postionWidth, int postionHeight){
        addActionListener(this);
        setBounds(postionWidth, postionHeight, LENGTHBUTTON, LENGTHBUTTON);
        setVisible(true);
        setRolloverEnabled(false);
        setBackground(new Color(129,235,103, 10));
        setFocusable(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(new Color(129,235,103, 10));
    }


    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isAbleMove() {
        return ableMove;
    }

    public void setAbleMove(boolean ableMove) {
        this.ableMove = ableMove;
    }
}
