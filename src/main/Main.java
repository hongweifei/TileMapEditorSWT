package main;


import org.eclipse.swt.widgets.*;

import fly.graphics.FlyRenderer;
import fly.graphics.FlyScene;


public class Main
{
	static int x=0,y=0;

	public static void main(String[] args)
	{
		Display display = new Display();

	    Shell shell = new Shell(display);
	    shell.setText("TileMapEditor");
	    shell.setSize (800,600);

	    FlyScene scene = new FlyScene(shell);
	    FlyRenderer renderer = scene.GetRenderer();
	    scene.SetRenderEvent(()->{
	    	renderer.DrawRect(x, y, 200, 200);
	    	x++;
	    	y++;
	    });

	    scene.SetRenderTimerExec(1000/30, display);

	    shell.open();


	    while (!shell.isDisposed())
	    {
	        if (!display.readAndDispatch())
	            display.sleep();
	    }
	    display.dispose();
	    scene.Release();
	}

}











