package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 小敌机:是飞行物，也是敌人能得分 */
public class Airplane extends FlyingObject implements Enemy {
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("airplane"+i+".png");
		}
	}
	
	private int speed; //移动速度
	/** 构造方法 */
	public Airplane(){
		super(49,36);
		speed = 2;
	}
	
	/** 重写step()移动 */
	public void step(){
		y+=speed; //y+(向下)
	}
	
	int deadIndex = 1; //死了的下标
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){ //每10毫秒走一次
		if(isLife()){ //若活着呢
			return images[0]; //返回images[0]
		}else if(isDead()){
			BufferedImage img = images[deadIndex++]; //从第2张开始轮转
			if(deadIndex==images.length){ //若到最后一张了
				state = REMOVE; //则修改当前状态为删除状态
			}
			return img;
			/*
			 * 死了的:
			 * 10M img=images[1] deadIndex=2 返回images[1]
			 * 20M img=images[2] deadIndex=3 返回images[2]
			 * 30M img=images[3] deadIndex=4 返回images[3]
			 * 40M img=images[4] deadIndex=5(REMOVE) 返回images[4]
			 * 50M 返回null
			 */
		}
		return null;
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //小敌机的y>=窗口的高，即为越界了 
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 1; //打掉小敌机，玩家得1分
	}
	
	
}














