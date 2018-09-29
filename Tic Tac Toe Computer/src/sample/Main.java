package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{	
	Random rand = new Random();
	char lastWin = 'X';

    public void start(Stage primaryStage) throws Exception
    {
        VBox root = new VBox();
        root.setId("root");
        HBox top = new HBox();
        top.setId("top");
        Button newGame = new Button("New Game");
        newGame.setId("newGame");
        Button quit = new Button("Quit");
        quit.setId("quit");
        GridPane grid = new GridPane();
        grid.setId("grid");
        Button btn[][] = new Button[3][3];
        Label winner = new Label();
        Label result = new Label();
        int i, j;
        for(i=0; i<3; i++)
            for(j=0; j<3; j++)
            {
                int x = i, y = j;
                btn[i][j] = new Button("");
                grid.add(btn[i][j], j, i);
                btn[i][j].setPrefWidth(70);
                btn[i][j].setPrefHeight(70);
                btn[i][j].setOnAction(e ->
                {
                    int xCount = 0, oCount = 0;
                    String win = "";
                    if(result.getText().equals(""))
                    {
                    	if(lastWin == 'X')
                    	{
	                        xCount = 0;
	                        for (int a = 0; a < 3; a++)
	                            for (int b = 0; b < 3; b++)
	                                if (btn[a][b].getText().equals("X"))
	                                    xCount++;
	                        if (btn[x][y].getText().equals(""))
	                        {
	                        	btn[x][y].setText("X");
	                            btn[x][y].setId("X");
	                            xCount++;
		                        win = check(btn);
			                    if (!win.equals(""))
			                    {
			                        winner.setId("winner");
			                        winner.setText("Winner");
			                        result.setId(win);
			                        result.setText(win);
			                        switch(win)
			                        {
			                        	case "X": 	lastWin = 'X';
			                        				break;
			                        	case "O":	lastWin = 'O';
			                        }
			                    }
			                    else if(xCount == 5)
			                    {
			                        winner.setId("winner");
			                        winner.setText("DRAW");
			                    }
			                    if(winner.getText().equals(""))
			                    	fillO(btn);
			                    win = check(btn);
			                    if (!win.equals(""))
			                    {
			                        winner.setId("winner");
			                        winner.setText("Winner");
			                        result.setId(win);
			                        result.setText(win);
			                        switch(win)
			                        {
			                        	case "X": 	lastWin = 'X';
			                        				break;
			                        	case "O":	lastWin = 'O';
			                        }
			                    }
	                        }
                    	}
                    	else
                    	{
                    		oCount = 0;
	                        for (int a = 0; a < 3; a++)
	                            for (int b = 0; b < 3; b++)
	                                if (btn[a][b].getText().equals("O"))
	                                    oCount++;
	                        if (btn[x][y].getText().equals(""))
	                        {
	                        	btn[x][y].setText("X");
	                            btn[x][y].setId("X");
	                        }
	                        win = check(btn);
		                    if (!win.equals(""))
		                    {
		                        winner.setId("winner");
		                        winner.setText("Winner");
		                        result.setId(win);
		                        result.setText(win);
		                        switch(win)
		                        {
		                        	case "X": 	lastWin = 'X';
		                        				break;
		                        	case "O":	lastWin = 'O';
		                        }
		                    }
		                    if(winner.getText().equals(""))
		                    {
		                    	fillO(btn);
		                    	oCount++;
		                    }
		                    win = check(btn);
		                    if (!win.equals(""))
		                    {
		                        winner.setId("winner");
		                        winner.setText("Winner");
		                        result.setId(win);
		                        result.setText(win);
		                        switch(win)
		                        {
		                        	case "X": 	lastWin = 'X';
		                        				break;
		                        	case "O":	lastWin = 'O';
		                        }
		                    }
		                    else if(oCount == 5)
		                    {
		                        winner.setId("winner");
		                        winner.setText("DRAW");
		                    }
                    	}
                    }
                });
            }

        newGame.setOnAction(e ->
        {
            for(int a = 0; a < 3; a++)
                for(int b = 0; b < 3; b++)
                {
                    btn[a][b].setText("");
                    btn[a][b].setId("empty");
                }
            if(lastWin == 'O')
            	fillO(btn);
            winner.setId("");
            winner.setText("");
            result.setId("");
            result.setText("");
        });

        quit.setOnAction(e -> System.exit(0));

        newGame.setPrefWidth(100);
        HBox.setMargin(newGame, new Insets(10, 0, 10, 15));
        quit.setPrefWidth(100);
        HBox.setMargin(quit, new Insets(10, 0, 10, 20));
        VBox.setMargin(grid, new Insets(20, 20, 20, 20));
        top.getChildren().addAll(newGame, quit);
        winner.setAlignment(Pos.CENTER);
        winner.setPrefWidth(70);
        VBox.setMargin(winner, new Insets(0, 0, 0, 90));
        result.setPrefWidth(70);
        result.setPrefHeight(70);
        result.setAlignment(Pos.CENTER);
        VBox.setMargin(result, new Insets(0, 0, 0, 90));
        root.getChildren().addAll(top, grid, winner, result);
        primaryStage.setTitle("Tic Tac Toe");
        Scene scene = new Scene(root, 250, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    String check(Button[][] a)
    {
        int i;
        for(i=0;i<3;i++)
        {
            if(a[i][0].getText().equals(a[i][1].getText()) && a[i][1].getText().equals(a[i][2].getText()) && !a[i][2].getText().equals(""))
                return(a[i][0].getText());
            else if(a[0][i].getText().equals(a[1][i].getText()) && a[1][i].getText().equals(a[2][i].getText()) && !a[2][i].getText().equals(""))
                return(a[0][i].getText());
        }
        if((a[0][0].getText().equals(a[1][1].getText()) && a[1][1].getText().equals(a[2][2].getText())) || (a[0][2].getText().equals(a[1][1].getText()) && a[1][1].getText().equals(a[2][0].getText())) && !a[1][1].getText().equals(""))
            return(a[1][1].getText());
        return("");
    }
    
    void fillO(Button btn[][])
    {
    	int a, b, xc = 0, oc = 0, oCount = 0;
    	for(a = 0; a < 3; a++)
    		for(b = 0; b < 3; b++)
    			if(btn[a][b].getText().equals("O"))
    				oCount++;
    	if((oCount < 4 && lastWin == 'X') || (oCount < 5 && lastWin == 'O'))
    	{
    		for(a = 0; a < 3; a++)
	    	{
	    		xc = oc = 0;
	    		for(b = 0; b < 3; b++)
	    			if(btn[a][b].getText().equals("O"))
	    				oc++;
	    			else if(btn[a][b].getText().equals("X"))
	    				xc++;
	    		if(oc==2)
	    			break;
	    	}
	    	if(oc==2 && xc==0)
	    	{
	    		for(b = 0; b <3; b++)
	    			if(btn[a][b].getText().equals(""))
	    			{
	    				btn[a][b].setText("O");
	    				btn[a][b].setId("O");
	    			}
	    	}
	    	else
	    	{
		    	for(a = 0; a < 3; a++)
		    	{
		    		xc = oc = 0;
		    		for(b = 0; b < 3; b++)
		    			if(btn[b][a].getText().equals("O"))
		    				oc++;
		    			else if(btn[b][a].getText().equals("X"))
		    				xc++;
		    		if(oc==2)
		    			break;
		    	}
		    	if(oc==2 && xc==0)
		    	{
		    		for(b = 0; b <3; b++)
		    			if(btn[b][a].getText().equals(""))
		    			{
		    				btn[b][a].setText("O");
		    				btn[b][a].setId("O");
		    			}
		    	}
		    	else
		    	{
		    		xc = oc = 0;
		    		for(a = 0; a < 3; a++)
		    			if(btn[a][a].getText().equals("O"))
		    				oc++;
		    			else if(btn[a][a].getText().equals("X"))
		    				xc++;
		    		if(oc==2 && xc==0)
		    		{
		    			for(a = 0; a < 3; a++)
		    				if(btn[a][a].getText().equals(""))
		    				{
		    					btn[a][a].setText("O");
		    					btn[a][a].setId("O");
		    				}
		    		}
		    		else
		    		{
		    			xc = oc = 0;
			    		for(a = 0; a < 3; a++)
			    			if(btn[a][2-a].getText().equals("O"))
			    				oc++;
			    			else if(btn[a][2-a].getText().equals("X"))
			    				xc++;
			    		if(oc==2 && xc==0)
			    		{
			    			for(a = 0; a < 3; a++)
			    				if(btn[a][2-a].getText().equals(""))
			    				{
			    					btn[a][2-a].setText("O");
			    					btn[a][2-a].setId("O");
			    				}
			    		}
			    		else
			        	{
			    			for(a = 0; a < 3; a++)
			    	    	{
			    	    		xc = oc = 0;
			    	    		for(b = 0; b < 3; b++)
			    	    			if(btn[a][b].getText().equals("O"))
			    	    				oc++;
			    	    			else if(btn[a][b].getText().equals("X"))
			    	    				xc++;
			    	    		if(xc==2)
			    	    			break;
			    	    	}
			    	    	if(xc==2 && oc==0)
			    	    	{
			    	    		for(b = 0; b <3; b++)
			    	    			if(btn[a][b].getText().equals(""))
			    	    			{
			    	    				btn[a][b].setText("O");
			    	    				btn[a][b].setId("O");
			    	    			}
			    	    	}
			    	    	else
			    	    	{
			    		    	for(a = 0; a < 3; a++)
			    		    	{
			    		    		xc = oc = 0;
			    		    		for(b = 0; b < 3; b++)
			    		    			if(btn[b][a].getText().equals("O"))
			    		    				oc++;
			    		    			else if(btn[b][a].getText().equals("X"))
			    		    				xc++;
			    		    		if(xc==2)
			    		    			break;
			    		    	}
			    		    	if(xc==2 && oc==0)
			    		    	{
			    		    		for(b = 0; b <3; b++)
			    		    			if(btn[b][a].getText().equals(""))
			    		    			{
			    		    				btn[b][a].setText("O");
			    		    				btn[b][a].setId("O");
			    		    			}
			    		    	}
			    		    	else
			    		    	{
			    		    		xc = oc = 0;
			    		    		for(a = 0; a < 3; a++)
			    		    			if(btn[a][a].getText().equals("O"))
			    		    				oc++;
			    		    			else if(btn[a][a].getText().equals("X"))
			    		    				xc++;
			    		    		if(xc==2 && oc==0)
			    		    		{
			    		    			for(a = 0; a < 3; a++)
			    		    				if(btn[a][a].getText().equals(""))
			    		    				{
			    		    					btn[a][a].setText("O");
			    		    					btn[a][a].setId("O");
			    		    				}
			    		    		}
			    		    		else
			    		    		{
			    		    			xc = oc = 0;
			    			    		for(a = 0; a < 3; a++)
			    			    			if(btn[a][2-a].getText().equals("O"))
			    			    				oc++;
			    			    			else if(btn[a][2-a].getText().equals("X"))
			    			    				xc++;
			    			    		if(xc==2 && oc==0)
			    			    		{
			    			    			for(a = 0; a < 3; a++)
			    			    				if(btn[a][2-a].getText().equals(""))
			    			    				{
			    			    					btn[a][2-a].setText("O");
			    			    					btn[a][2-a].setId("O");
			    			    				}
			    			    		}
			    			    		else
			    			        	{
			    			        		do
			    			        		{
			    			        			a = rand.nextInt(3);
			    			        			b = rand.nextInt(3);
			    			        		}while(btn[a][b].getText().equals("X") || btn[a][b].getText().equals("O"));
			    			        		btn[a][b].setText("O");
			    			        		btn[a][b].setId("O");
			    			        	}
			    		    		}
			    		    	}
			    	    	}
			        	}
		    		}
		    	}
	    	}
    	}
    }
    
    public static void main(String[] args)
{
        launch(args);
    }
}
