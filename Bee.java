package cn.tedu.shoot;
import java.util.Random;
import java.awt.image.BufferedImage;
/** 小蜜蜂:是飞行物，也是奖励 */
public class Bee extends FlyingObject implements Award{
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("bee"+i+".png");
		}
	}
	
	private int xSpeed; //x坐标移动速度
	private int ySpeed; //y坐标移动速度
	private int awardType; //奖励类型(0或1)
	/** 构造方法 */
	public Bee(){
		super(60,50);
		xSpeed = 1;
		ySpeed = 2;
		Random rand = new Random();
		awardType = rand.nextInt(2); //0或1
	}
	
	/** 重写step()移动 */
	public void step(){
		x+=xSpeed; //x+(向左或向右)
		y+=ySpeed; //y+(向下)
		if(x<=0 || x>=World.WIDTH-this.width){ //到两头了
			xSpeed*=-1; //切换正负(切换方向)
		}
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
		return this.y>=World.HEIGHT; //小蜜蜂的y>=窗口的高，即为越界了 
	}
	
	/** 重写getAwardType()获取奖励 */
	public int getAwardType(){
		return awardType; //返回奖励类型
	}
	
}
















