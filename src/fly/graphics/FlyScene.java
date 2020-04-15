package fly.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class FlyScene
{
	private Canvas canvas;
	private FlyRenderer renderer;

	public FlyScene(Shell shell)
	{
		canvas = new Canvas(shell,SWT.DOUBLE_BUFFERED);
		renderer = new FlyRenderer(this);

		canvas.setSize(shell.getSize());

		shell.addListener(SWT.Resize, e->{
			canvas.setSize(shell.getSize());
		});

		canvas.addListener(SWT.Paint, e->{
			renderer.Clear(canvas.getSize());
			renderer.Render();
		});
	}


	/**
	 * �ͷų���
	 * */
	public void Release()
	{
		this.canvas.dispose();
		this.renderer.Release();
	}

	/**
	 * ��ȡ�����Ƿ��ͷ�
	 *
	 * @return ������ͷţ��򷵻�true�����򷵻�false
	 * */
	public boolean IsRelease()
	{
		return this.canvas.isDisposed();
	}


	/**
	 * ��ȡ����
	 *
	 * @return ���ػ���
	 * */
	public Canvas GetCanvas()
	{
		return this.canvas;
	}


	/**
	 * ��ȡ��Ⱦ��
	 * */
	public FlyRenderer GetRenderer()
	{
		return this.renderer;
	}



	/**
	 * ������Ⱦ��ʱִ����
	 * */
	public void SetRenderTimerExec(int milliseconds, Display display)
	{
		display.timerExec (milliseconds, new Runnable () {
			@Override public void run ()
			{
				if (IsRelease())
					return;
				// canvas.redraw (); // <-- bad, damages more than is needed

				Point extent = renderer.GetGC().stringExtent (System.currentTimeMillis() + "0");
				canvas.redraw (0, 0, extent.x, extent.y, false);
				display.timerExec (milliseconds, this);
			}
		});
	}



	/**
	 * ���ó�������Ⱦ������Ⱦ�¼�
	 * */
	public void SetRenderEvent(Event event)
	{
		this.renderer.SetRenderEvent(event);
		canvas.addListener(SWT.Paint, e->{
			renderer.Render();
		});
	}


	/**
	 * ��ӳ����ı��С������
	 * */
	public void AddResizeListener(Listener listener)
	{
		canvas.addListener(SWT.Resize, listener);
	}
}















