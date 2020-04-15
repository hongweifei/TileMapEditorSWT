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
	 * 释放场景
	 * */
	public void Release()
	{
		this.canvas.dispose();
		this.renderer.Release();
	}

	/**
	 * 获取场景是否释放
	 *
	 * @return 如果已释放，则返回true，否则返回false
	 * */
	public boolean IsRelease()
	{
		return this.canvas.isDisposed();
	}


	/**
	 * 获取画布
	 *
	 * @return 返回画布
	 * */
	public Canvas GetCanvas()
	{
		return this.canvas;
	}


	/**
	 * 获取渲染器
	 * */
	public FlyRenderer GetRenderer()
	{
		return this.renderer;
	}



	/**
	 * 设置渲染定时执行器
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
	 * 设置场景中渲染器的渲染事件
	 * */
	public void SetRenderEvent(Event event)
	{
		this.renderer.SetRenderEvent(event);
		canvas.addListener(SWT.Paint, e->{
			renderer.Render();
		});
	}


	/**
	 * 添加场景改变大小监听器
	 * */
	public void AddResizeListener(Listener listener)
	{
		canvas.addListener(SWT.Resize, listener);
	}
}















