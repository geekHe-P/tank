import java.awt.*;
import java.util.Random;

public class Tank {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;
    private boolean live = true;
    private boolean moving = true;
    private Group group = Group.BAD;

    private Random random = new  Random();

    public Group getGroup() {
        return group;
    }

    private static final int SPEED = 8;
    private Dir dir;
    TankFrame tankFrame = null;

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    //绘画
    public void paint(Graphics g) {
        if (!live) {
            tankFrame.tankList.remove(this);
        }

        switch (dir) {
            case UP:
                g.drawImage(ResourceImage.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceImage.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceImage.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceImage.tankR, x, y, null);
                break;
        }

        if (!moving) return;
        move();
    }

    //移动
    private void move() {
        switch (this.dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (random.nextInt(10) > 8) this.fire();
    }

    //开火
    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x, this.y, this.dir, tankFrame, this.group));
    }

    //死亡判断
    public void die() {
        live = false;
    }
}
