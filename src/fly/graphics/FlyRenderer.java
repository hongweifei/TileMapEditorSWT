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
	 * �ͷ���Ⱦ��
	 * */
	public void Release()
	{
		gc.dispose();
	}



	/**
	 * ��ȡ��Ⱦ���Ƿ��ͷ�
	 *
	 * @return ������ͷţ��򷵻�true�����򷵻�false
	 * */
	public boolean IsRelease()
	{
		return gc.isDisposed();
	}



	/**
	 * ��ȡGC
	 * */
	public GC GetGC()
	{
		return this.gc;
	}


	/**
	 * ������Ⱦ�¼�
	 *
	 * @param event Ҫ���õ���Ⱦ�¼�
	 * */
	public void SetRenderEvent(Event event)
	{
		render_event = event;
	}


	/**
	 * ִ����Ⱦ�¼�
	 * */
	public void Render()
	{
		this.render_event.invoke();
	}


	/**
	 * ���ָ������
	 * */
	public void Clear(Point point)
	{
		gc.fillRectangle(0, 0, point.x, point.y);
	}



	/**
	 * ���ָ������
	 * */
	public void Clear(int width,int height)
	{
		gc.fillRectangle(0, 0, width, height);
	}



	/**
	 * ��������
	 * ʹ��ǰ��ɫ�ڵ㣨x1��y1���ͣ�x2��y2��֮�����һ���ߡ�
	 *
	 * @param x1 ���Ƶĵ�һ����ĺ�����
	 * @param y1 ���Ƶĵ�һ�����������
	 * @param x2 ���Ƶĵڶ�����ĺ�����
	 * @param y2 ���Ƶĵڶ������������
	 * */
	public void DrawLine(int x1,int y1,int x2,int y2)
	{
		gc.drawLine(x1, y1, x2, y2);
	}


	/**
	 * ���ƾ���
	 *
	 * @param x ���ƺ�����
	 * @param y ����������
	 * @param width ���ƿ��
	 * @param height ���Ƹ߶�
	 * */
	public void DrawRect(int x,int y,int width,int height)
	{
		gc.drawRectangle(x, y, width, height);
	}

	/**
	 * ���ƾ���
	 * ʹ��ǰ��ɫ����ָ�����ε�������
	 * ���ε����ұ�Եλ��rect.x��rect.x+rect.width����
	 * �����͵ײ���Եλ��rect.y��rect.y+rect.height��
	 *
	 * @param rect Ҫ���Ƶľ���
	 * */
	public void DrawRect(Rectangle rect)
	{
		gc.drawRectangle(rect);
	}

	/**
	 * �����ı�
	 * ʹ�ý������ĵ�ǰ�����ǰ��ɫ���Ƹ����ַ���������ҳǩչ���ͻس�����
	 * �����ı��ľ�������ı�������䱳��ɫ��
	 *
	 * @param text Ҫ���Ƶ��ı�
	 * @param x ���ƺ�����
	 * @param y ����������
	 * */
	public void DrawText(String text,int x,int y)
	{
		gc.drawText(text, x, y);
	}


	/**
	 * �����ı�
	 * ʹ�õ�ǰ�����ǰ��ɫ���Ƹ����ַ�����
	 * ����ҳǩչ���ͻس��������is_transparentΪtrue��
	 * �򲻻��޸����ڻ����ı��ľ�������ı�����������䱳��ɫ��
	 *
	 * @param text Ҫ���Ƶ��ı�
	 * @param x ���ƺ�����
	 * @param y ����������
	 * @param is_transparent ���Ϊ�棬�򱳾�Ϊ͸��������Ϊ��͸��
	 * */
	public void DrawText(String text,int x,int y,boolean is_transparent)
	{
		gc.drawText(text, x, y, is_transparent);
	}



	/**
	 * �����ı�
	 * ʹ�õ�ǰ�����ǰ��ɫ���Ƹ����ַ�����
	 * ����ָ���ı�־ִ���Ʊ����չ���зָ��������Ƿ�����
	 * �����־����DRAW_TRANSPARENT���򲻻��޸����ڻ����ı��ľ�������ı����������ñ���ɫ���
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
	 * @param text Ҫ���Ƶ��ı�
	 * @param x ���ƺ�����
	 * @param y ����������
	 * @param flags ָ����δ����ı��ı�־
	 * */
	public void DrawText(String text,int x,int y,int flags)
	{
		gc.drawText(text, x, y, flags);
	}


	/**
	 * ʹ�õ�ǰ�����ǰ��ɫ���Ƹ����ַ�����������ҳǩ��չ��س����������ַ����ľ�������ı�������䱳��ɫ��
	 * ��Windows�ϣ�DrawString��DrawText���в�ͬ
	 *
	 * @param str Ҫ���Ƶ��ı�
	 * @param x ���ƺ�����
	 * @param y ����������
	 * */
	public void DrawString(String str,int x,int y)
	{
		gc.drawString(str, x, y);
	}

	/**
	 * ʹ�õ�ǰ�����ǰ��ɫ���Ƹ����ַ�����
	 * ������ҳǩ��չ��س�����
	 * ���is_transparentΪtrue���򲻻��޸Ļ����ַ����ľ�������ı���������ʹ�ñ���ɫ��䡣
	 *
	 * <br><br>
	 * ��Windows�ϣ�drawString��drawText���в�ͬ��
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
	 * @param str Ҫ���Ƶ��ı�
	 * @param x ���ƺ�����
	 * @param y ����������
	 * @param is_transparent ���Ϊ�棬�򱳾�Ϊ͸��������Ϊ��͸��
	 * */
	public void DrawString(String str,int x,int y,boolean is_transparent)
	{
		gc.drawString(str, x, y, is_transparent);
	}

	/**
	 * ��ָ�����괦���Ƹ���ͼ��
	 *
	 * @param image Ҫ���Ƶ�ͼ��
	 * @param x ����λ�õ�x����
	 * @param y ����λ�õ�y����
	 * */
	public void DrawImage(Image image,int x,int y)
	{
		gc.drawImage(image, x, y);
	}


	/**
	 * ��Դͼ���еľ��������Ƶ��������еľ������򣨿��ܴ�С��ͬ����
	 * ���Դ�����Ŀ�������С��ͬ�����ڸ���Դ����ʱ��Դ���򽫱�������������ʺ�Ŀ������
	 * ���Դ���ε��κβ���λ��Դͼ��ı߽�֮�⣬��������κο�Ȼ�߶Ȳ���Ϊ��������ʧ�ܡ�
	 *
	 * @param image Ҫ���Ƶ�ͼ��
	 * @param dest_x the x Ҫ���Ƶ���Ŀ���е�x����
	 * @param dest_y the y Ҫ���Ƶ���Ŀ���е�y����
	 * @param dest_width Ŀ����εĿ�ȣ��Ե�Ϊ��λ��
	 * @param dest_height Ŀ����εĸ߶ȣ��Ե�Ϊ��λ��
	 * @param src_x Ҫ���и��Ƶ�Դͼ���е�x����
	 * @param src_y Ҫ���и��Ƶ�Դͼ���е�y����
	 * @param src_width Ҫ��Դ���Ƶĵ�Ŀ��
	 * @param src_height Ҫ��Դ���Ƶĵ�ĸ߶�
	 * */
	public void DrawImage(Image image,int dest_x,int dest_y,
			int dest_width,int dest_height,int src_x,int src_y,int src_width,int src_height)
	{
		gc.drawImage(image, src_x, src_y, src_width, src_height, dest_x, dest_y, dest_width, dest_height);
	}
}





















