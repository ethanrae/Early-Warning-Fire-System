package source;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author E
 */
public class Grid_Cell extends JLabel implements Comparable<Grid_Cell> {

    private int x;
    private int y;
    private boolean selected;

    public Grid_Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.selected = false;
        this.setText("•");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(true);
    }

    public int getCellX() {
        return this.x;
    }

    public int getCellY() {
        return this.y;
    }

    public void setCellX(int x) {
        this.x = x;
    }

    public void setCellY(int y) {
        this.y = y;
    }

    public void setSelected() {
        this.selected = true;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        if (!Controller.getSelectedIndexs().contains(this)) {
            Controller.getSelectedIndexs().add(this);
        }
    }

    public void setNotSelected() {
        this.selected = false;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Controller.getSelectedIndexs().remove(this);
    }

    public boolean isSelected() {
        return selected;
    }

    public int getIndex() {
        return (this.x * 32) + this.y;
    }

    @Override
    public int compareTo(Grid_Cell o) {
        return (this.getIndex() - o.getIndex());
    }

    @Override
    public String toString() {
        return "" + this.x + "," + this.y + " > " + this.getIndex() + "\t" + this.selected;

    }
}
