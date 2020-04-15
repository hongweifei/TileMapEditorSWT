package fly.graphics;


import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;


public class FlyRenderer
{

	private GC gc;
	private Event render_event;

	public FlyRenderer(FlyScene scene)
	{
		gc = new GC(scene.GetCanvas());
		render_event = new Event(){@Override public void invoke() {}};
	}


	/**
	 * 释放渲染器
	 * */
	public void Release()
	{
		gc.dispose();
	}



	/**
	 * 获取渲染器是否释放
	 *
	 * @return 如果已释放，则返回true，否则返回false
	 * */
	public boolean IsRelease()
	{
		return gc.isDisposed();
	}



	/**
	 * 获取GC
	 * */
	public GC GetGC()
	{
		return this.gc;
	}


	/**
	 * 设置渲染事件
	 *
	 * @param event 要设置的渲染事件
	 * */
	public void SetRenderEvent(Event event)
	{
		render_event = event;
	}


	/**
	 * 执行渲染事件
	 * */
	public void Render()
	{
		this.render_event.invoke();
	}


	/**
	 * 清空指定区域
	 * */
	public void Clear(Point point)
	{
		gc.fillRectangle(0, 0, point.x, point.y);
	}



	/**
	 * 清空指定区域
	 * */
	public void Clear(int width,int height)
	{
		gc.fillRectangle(0, 0, width, height);
	}



	/**
	 * 绘制线条
	 * 使用前景色在点（x1，y1）和（x2，y2）之间绘制一条线。
	 *
	 * @param x1 绘制的第一个点的横坐标
	 * @param y1 绘制的第一个点的纵坐标
	 * @param x2 绘制的第二个点的横坐标
	 * @param y2 绘制的第二个点的纵坐标
	 * */
	public void DrawLine(int x1,int y1,int x2,int y2)
	{
		gc.drawLine(x1, y1, x2, y2);
	}


	/**
	 * 绘制矩形
	 *
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * */
	public void DrawRect(int x,int y,int width,int height)
	{
		gc.drawRectangle(x, y, width, height);
	}

	/**
	 * 绘制矩形
	 * 使用前景色绘制指定矩形的轮廓。
	 * 矩形的左右边缘位于rect.x和rect.x+rect.width处。
	 * 顶部和底部边缘位于rect.y和rect.y+rect.height。
	 *
	 * @param rect 要绘制的矩形
	 * */
	public void DrawRect(Rectangle rect)
	{
		gc.drawRectangle(rect);
	}

	/**
	 * 绘制文本
	 * 使用接收器的当前字体和前景色绘制给定字符串。进行页签展开和回车处理。
	 * 绘制文本的矩形区域的背景将填充背景色。
	 *
	 * @param text 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * */
	public void DrawText(String text,int x,int y)
	{
		gc.drawText(text, x, y);
	}


	/**
	 * 绘制文本
	 * 使用当前字体和前景色绘制给定字符串。
	 * 进行页签展开和回车处理。如果is_transparent为true，
	 * 则不会修改正在绘制文本的矩形区域的背景，否则将填充背景色。
	 *
	 * @param text 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param is_transparent 如果为真，则背景为透明，否则为不透明
	 * */
	public void DrawText(String text,int x,int y,boolean is_transparent)
	{
		gc.drawText(text, x, y, is_transparent);
	}



	/**
	 * 绘制文本
	 * 使用当前字体和前景色绘制给定字符串。
	 * 根据指定的标志执行制表符扩展、行分隔符和助记符处理。
	 * 如果标志包括DRAW_TRANSPARENT，则不会修改正在绘制文本的矩形区域的背景，否则将用背景色填充
	 *
	 * <p>
	 * The parameter <code>flags</code> may be a combination of:
	 * </p>
	 * <dl>
	 * <dt><b>DRAW_DELIMITER</b></dt>
	 * <dd>draw multiple lines</dd>
	 * <dt><b>DRAW_TAB</b></dt>
	 * <dd>expand tabs</dd>
	 * <dt><b>DRAW_MNEMONIC</b></dt>
	 * <dd>underline the mnemonic character</dd>
	 * <dt><b>DRAW_TRANSPARENT</b></dt>
	 * <dd>transparent background</dd>
	 * </dl>
	 *
	 * @param text 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param flags 指定如何处理文本的标志
	 * */
	public void DrawText(String text,int x,int y,int flags)
	{
		gc.drawText(text, x, y, flags);
	}


	/**
	 * 使用当前字体和前景色绘制给定字符串。不进行页签扩展或回车处理。绘制字符串的矩形区域的背景将填充背景色。
	 * 在Windows上，DrawString和DrawText略有不同
	 *
	 * @param str 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * */
	public void DrawString(String str,int x,int y)
	{
		gc.drawString(str, x, y);
	}

	/**
	 * 使用当前字体和前景色绘制给定字符串。
	 * 不进行页签扩展或回车处理。
	 * 如果is_transparent为true，则不会修改绘制字符串的矩形区域的背景，否则将使用背景色填充。
	 *
	 * <br><br>
	 * 在Windows上，drawString和drawText略有不同：
	 *
	 * <ul>
	 *     <li>DrawString is faster (depends on string size)
	 *     	<br>~7x for 1-char strings
	 *     	<br>~4x for 10-char strings
	 *     	<br>~2x for 100-char strings
	 *     </li>
	 *
	 *     <li>DrawString doesn't try to find a good fallback font when character doesn't have a glyph in currently selected font</li>
	 * </ul>
	 *
	 * @param str 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param is_transparent 如果为真，则背景为透明，否则为不透明
	 * */
	public void DrawString(String str,int x,int y,boolean is_transparent)
	{
		gc.drawString(str, x, y, is_transparent);
	}

	/**
	 * 在指定坐标处绘制给定图像。
	 *
	 * @param image 要绘制的图像
	 * @param x 绘制位置的x坐标
	 * @param y 绘制位置的y坐标
	 * */
	public void DrawImage(Image image,int x,int y)
	{
		gc.drawImage(image, x, y);
	}


	/**
	 * 将源图像中的矩形区域复制到接收器中的矩形区域（可能大小不同）。
	 * 如果源区域和目标区域大小不同，则在复制源区域时，源区域将被拉伸或收缩以适合目标区域。
	 * 如果源矩形的任何部分位于源图像的边界之外，或者如果任何宽度或高度参数为负，则复制失败。
	 *
	 * @param image 要绘制的图像
	 * @param dest_x the x 要复制到的目标中的x坐标
	 * @param dest_y the y 要复制到的目标中的y坐标
	 * @param dest_width 目标矩形的宽度（以点为单位）
	 * @param dest_height 目标矩形的高度（以点为单位）
	 * @param src_x 要从中复制的源图像中的x坐标
	 * @param src_y 要从中复制的源图像中的y坐标
	 * @param src_width 要从源复制的点的宽度
	 * @param src_height 要从源复制的点的高度
	 * */
	public void DrawImage(Image image,int dest_x,int dest_y,
			int dest_width,int dest_height,int src_x,int src_y,int src_width,int src_height)
	{
		gc.drawImage(image, src_x, src_y, src_width, src_height, dest_x, dest_y, dest_width, dest_height);
	}
}





















