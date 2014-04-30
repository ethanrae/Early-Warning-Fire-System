package source;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author E
 */
public class Grid_Cell extends JLabel {

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
        this.selected = !this.selected;
        if (this.selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    public boolean isSelected() {
        return selected;
    }
}
