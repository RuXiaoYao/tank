package com.tencent.tank;

import com.tencent.tank.abstractfactory.BaseTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Random;

public class Tank extends BaseTank {

    private int x,y;
    private Dir dir;
    private static final int SPEED = 6;

    private boolean moving = true;
    private boolean living = true;

    private Random random = new Random();

    public static final int WIDTH = ResourceMgr.badTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.badTankU.getHeight();

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    TankFrame tf = null;
    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();
=======
    public TankFrame tf = null;
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
=======
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
    public TankFrame tf = null;

    FireStrategy fs = null;
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")

    public Tank(int x,int y,Dir dir,Group group,TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
<<<<<<< HEAD
=======

        if(this.group == Group.GOOD){
            String goodFS = (String)PropertyMgr.get("goodFS");

            //JDK1.9之后不推荐使用newInstance()
//                fs = (FourDirFireStrategy)Class.forName(goodFS).newInstance();
            try {
                fs = (FourDirFireStrategy)Class.forName(goodFS).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            fs = new DefaultFireStrategy();
        }
<<<<<<< HEAD
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void paint(Graphics g){
        if(!living){
            tf.enemiesTank.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD,x,y,null);
                break;
        }

        move();
    }

    public void move(){
        if(!moving) return;

        switch(dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if(this.group == Group.BAD && random.nextInt(100) > 94){
            this.fire();
        }

        if(this.group == Group.BAD &&random.nextInt(100) > 94){
            randomDir();
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) {
            x = 2;
        }
        if(this.y < 28){
            y = 28;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH -2){
            x = TankFrame.GAME_WIDTH- Tank.WIDTH -2;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2  ;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        int bX = this.x + WIDTH/2 -Bullet.WIDTH/2;
        int bY = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
=======
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        for(Dir dir : Dir.values()){
//            new Bullet(bX,bY,dir,t.group,t.tf);
            this.tf.gf.createBullet(bX,bY,dir,this.group,this.tf);
        }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")
=======
>>>>>>> parent of a7ed9c8 (Revert "代码重构-使用抽象工厂，动态添加产品族")

        if(this.group == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    @Override
    public void die() {
        this.living = false;
    }
}
