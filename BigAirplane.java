package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 大敌机:是飞行物，也是敌人能得分 */
public class BigAirplane extends FlyingObject implements Enemy{
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("bigplane"+i+".png");
		}
	}
	
	private int speed;
	/** 构造方法 */
	public BigAirplane(){
		super(69,99);
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
		}
		return null;
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //大敌机的y>=窗口的高，即为越界了 
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 3; //打掉大敌机，玩家得3分
	}
	
}











