package com.sulient.pixelclicker;

import com.sulient.pixelclicker.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;
import java.lang.Math;


@SuppressLint("CommitPrefEdits") public class GameActivity extends Activity {
	//////////DEKLARACJA ZMIENNYCH (ceny, iloœæ itp.: ekranów)//////////   
	double 	pixels;
	double	PpS;
	float   global_multiplier;

	long tap_multipler;
	int tap_x2_amount;
	long tap_x2_price;

	int pps_up_amount;
	long pps_up_price;
	float pps_multipler;

	int coursor=1;				//ilosc otrzymanych PpS po zakupie                
	int coursor_amount;		//ilosc zakupionych elementow        
	long coursor_price;		//cena elementu

	int calculator=5;            
	int calculator_amount;     
	long calculator_price;    

	int cell_phone=20;           
	int cell_phone_amount;       
	long cell_phone_price;    

	int smartphone=120;           
	int smartphone_amount;       
	long smartphone_price;    

	int tablet=500;           
	int tablet_amount;      
	long tablet_price;

	int monitor=2000;           
	int monitor_amount;     
	long monitor_price;

	int television=10000;           
	int television_amount;       
	long television_price;

	int cinema_screen=50000;           
	int cinema_screen_amount;       	
	long cinema_screen_price;

	int satellite_screen=200000;           
	int satellite_screen_amount;       	
	long satellite_screen_price;
	
	int moon_screen=1000000;           
	int moon_screen_amount;       	
	long moon_screen_price;
	
	int sun_screen=10000000;           
	int sun_screen_amount;      	
	long sun_screen_price;
	
	int solar_system_screen=100000000;           
	int solar_system_screen_amount;       	
	long solar_system_screen_price;
	
	boolean show_tutorial;
	
	//////////DEKLARACJA ANIMACJI//////////   
    Animation anim_fade_out;
    Animation anim_zoom_in;
    
    //////////DEKLARACJA ZAPISU/ODCZYTU//////////   
    public static final String PREFS_NAME = "MyPreferencesFile";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //////////USTAWIENIE APLIKACJI NA PE£NY EKRAN//////////  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        //////////WCZYTYWANIE DANYCH I WYPISANIE DANYCH(tylko cen)//////////      
        SharedPreferences game_progress = getSharedPreferences(PREFS_NAME, 0);
        
        pixels = (double) game_progress.getLong("pixels", 0L); 
        PpS = (double) game_progress.getLong("PpS", 0L);
        final TextView pixels_amount_text = (TextView) findViewById(R.id.pixels_amount_text);
        final TextView PpS_amount_text = (TextView) findViewById(R.id.PpS_amount_text);
        
        final TextView pixel_taped_text = (TextView) findViewById(R.id.pixel_taped_text);
    	pixel_taped_text.setText("+"+tap_multipler);  

        tap_multipler = game_progress.getLong("tap_multipler", 1); 
        tap_x2_amount = game_progress.getInt("tap_x2_amount", 0); 
        tap_x2_price = game_progress.getLong("tap_x2_price", 100L);    
    	final TextView tap_x2_amount_text = (TextView) findViewById(R.id.tap_x2_amount_text);
    	tap_x2_amount_text.setText("A: "+tap_x2_amount); 
    	final TextView tap_x2_price_text = (TextView) findViewById(R.id.tap_x2_price_text);
    	if (tap_x2_price < 1000000) 													{tap_x2_price_text.setText("P: "+tap_x2_price);}                
    	else if ((tap_x2_price >= 1000000) && (tap_x2_price < 1000000000)) 				{tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000)/100)+" milion");}
    	else if ((tap_x2_price >= 1000000000) && (tap_x2_price < 1000000000000L))		{tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000000)/100)+" billion");}
    	else if ((tap_x2_price >= 1000000000000L) && (tap_x2_price < 1000000000000000L)){tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000000000L)/100)+" trillion");} 
        
    	pps_multipler = game_progress.getFloat("pps_multipler", (float) 1.00);      
    	pps_up_amount = game_progress.getInt("pps_up_amount", 0); 	
        pps_up_price = game_progress.getLong("pps_up_price", 100L);
	    final TextView pps_up_amount_text = (TextView) findViewById(R.id.pps_up_amount_text);
	    pps_up_amount_text.setText("A: "+pps_up_amount);
    	final TextView pps_up_price_text = (TextView) findViewById(R.id.pps_up_price_text);
    	if (pps_up_price < 1000000) 													{pps_up_price_text.setText("P: "+pps_up_price);}                
    	else if ((pps_up_price >= 1000000) && (pps_up_price < 1000000000)) 				{pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000)/100)+" milion");}
    	else if ((pps_up_price >= 1000000000) && (pps_up_price < 1000000000000L))		{pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000000)/100)+" billion");}   
    	else if ((pps_up_price >= 1000000000000L) && (pps_up_price < 1000000000000000L)){pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000000000L)/100)+" trillion");}  	
	    
        coursor_amount = game_progress.getInt("coursor_amount", 0); 
        coursor_price = game_progress.getLong("coursor_price", 15L);
    	final TextView coursor_amount_text = (TextView) findViewById(R.id.coursor_amount_text);
    	coursor_amount_text.setText("Amount: "+coursor_amount);
    	final TextView coursor_price_text = (TextView) findViewById(R.id.coursor_price_text);
    	if (coursor_price < 1000000) 														{coursor_price_text.setText("Price: "+coursor_price);}                
    	else if ((coursor_price >= 1000000) && (coursor_price < 1000000000)) 				{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000)/100)+" milion");}
    	else if ((coursor_price >= 1000000000) && (coursor_price < 1000000000000L))			{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000000)/100)+" billion");}
    	else if ((coursor_price >= 1000000000000L) && (coursor_price < 1000000000000000L))	{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000000000L)/100)+" trillion");} 
	 
        calculator_amount = game_progress.getInt("calculator_amount", 0); 
        calculator_price = game_progress.getLong("calculator_price", 500L);
    	final TextView calculator_amount_text = (TextView) findViewById(R.id.calculator_amount_text);
    	calculator_amount_text.setText("Amount: "+calculator_amount);  
    	final TextView calculator_price_text = (TextView) findViewById(R.id.calculator_price_text);
    	if (calculator_price < 1000000) 														{calculator_price_text.setText("Price: "+calculator_price);}                
    	else if ((calculator_price >= 1000000) && (calculator_price < 1000000000)) 				{calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000)/100)+" milion");}
    	else if ((calculator_price >= 1000000000) && (calculator_price < 1000000000000L))		{calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000000)/100)+" billion");}
    	else if ((calculator_price >= 1000000000000L) && (calculator_price < 1000000000000000L)){calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000000000L)/100)+" trillion");} 
 
        cell_phone_amount = game_progress.getInt("cell_phone_amount", 0); 
        cell_phone_price = game_progress.getLong("cell_phone_price", 4000L); 
    	final TextView cell_phone_amount_text = (TextView) findViewById(R.id.cell_phone_amount_text);
    	cell_phone_amount_text.setText("Amount: "+cell_phone_amount);
    	final TextView cell_phone_price_text = (TextView) findViewById(R.id.cell_phone_price_text);
    	if (cell_phone_price < 1000000) 														{cell_phone_price_text.setText("Price: "+cell_phone_price);}                
    	else if ((cell_phone_price >= 1000000) && (cell_phone_price < 1000000000)) 				{cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000)/100)+" milion");}
    	else if ((cell_phone_price >= 1000000000) && (cell_phone_price < 1000000000000L))		{cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000000)/100)+" billion");}
    	else if ((cell_phone_price >= 1000000000000L) && (cell_phone_price < 1000000000000000L)){cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000000000L)/100)+" trillion");} 

        smartphone_amount = game_progress.getInt("smartphone_amount", 0); 
        smartphone_price = game_progress.getLong("smartphone_price", 50000L);
    	final TextView smartphone_amount_text = (TextView) findViewById(R.id.smartphone_amount_text);
    	smartphone_amount_text.setText("Amount: "+smartphone_amount);
    	final TextView smartphone_price_text = (TextView) findViewById(R.id.smartphone_price_text);
    	if (smartphone_price < 1000000) 														{smartphone_price_text.setText("Price: "+smartphone_price);}                
    	else if ((smartphone_price >= 1000000) && (smartphone_price < 1000000000)) 				{smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000)/100)+" milion");}
    	else if ((smartphone_price >= 1000000000) && (smartphone_price < 1000000000000L))		{smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000000)/100)+" billion");}
    	else if ((smartphone_price >= 1000000000000L) && (smartphone_price < 1000000000000000L)){smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000000000L)/100)+" trillion");} 
    	
        tablet_amount = game_progress.getInt("tablet_amount", 0); 
        tablet_price = game_progress.getLong("tablet_price", 400000L); 
    	final TextView tablet_amount_text = (TextView) findViewById(R.id.tablet_amount_text);
    	tablet_amount_text.setText("Amount: "+tablet_amount);
    	final TextView tablet_price_text = (TextView) findViewById(R.id.tablet_price_text); 
    	if (tablet_price < 1000000) 													{tablet_price_text.setText("Price: "+tablet_price);}                
    	else if ((tablet_price >= 1000000) && (tablet_price < 1000000000)) 				{tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000)/100)+" milion");}
    	else if ((tablet_price >= 1000000000) && (tablet_price < 1000000000000L))		{tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000000)/100)+" billion");}
    	else if ((tablet_price >= 1000000000000L) && (tablet_price < 1000000000000000L)){tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000000000L)/100)+" trillion");} 
    	   
        monitor_amount = game_progress.getInt("monitor_amount", 0); 
        monitor_price = game_progress.getLong("monitor_price", 2500000L); 
    	final TextView monitor_amount_text = (TextView) findViewById(R.id.monitor_amount_text);
    	monitor_amount_text.setText("Amount: "+monitor_amount);
    	final TextView monitor_price_text = (TextView) findViewById(R.id.monitor_price_text);
    	if (monitor_price < 1000000) 														{monitor_price_text.setText("Price: "+monitor_price);}                
    	else if ((monitor_price >= 1000000) && (monitor_price < 1000000000)) 				{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000)/100)+" milion");}
    	else if ((monitor_price >= 1000000000) && (monitor_price < 1000000000000L))			{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000000)/100)+" billion");}
    	else if ((monitor_price >= 1000000000000L) && (monitor_price < 1000000000000000L))	{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000000000L)/100)+" trillion");} 
    	 
        television_amount = game_progress.getInt("television_amount", 0); 
        television_price = game_progress.getLong("television_price", 20000000L); 
    	final TextView television_amount_text = (TextView) findViewById(R.id.television_amount_text);
    	television_amount_text.setText("Amount: "+television_amount);  
    	final TextView television_price_text = (TextView) findViewById(R.id.television_price_text);
    	if (television_price < 1000000) 														{television_price_text.setText("Price: "+television_price);}                
    	else if ((television_price >= 1000000) && (television_price < 1000000000)) 				{television_price_text.setText("Price: "+(Math.floor(television_price/10000)/100)+" milion");}
    	else if ((television_price >= 1000000000) && (television_price < 1000000000000L))		{television_price_text.setText("Price: "+(Math.floor(television_price/10000000)/100)+" billion");} 
    	else if ((television_price >= 1000000000000L) && (television_price < 1000000000000000L)){television_price_text.setText("Price: "+(Math.floor(television_price/10000000000L)/100)+" trillion");}   	    
   
    	cinema_screen_amount = game_progress.getInt("cinema_screen_amount", 0); 
    	cinema_screen_price = game_progress.getLong("cinema_screen_price", 150000000L);
    	final TextView cinema_screen_amount_text = (TextView) findViewById(R.id.cinema_screen_amount_text);
    	cinema_screen_amount_text.setText("Amount: "+cinema_screen_amount);  
    	final TextView cinema_screen_price_text = (TextView) findViewById(R.id.cinema_screen_price_text);
    	if (cinema_screen_price < 1000000) 																{cinema_screen_price_text.setText("Price: "+cinema_screen_price);}                
    	else if ((cinema_screen_price >= 1000000) && (cinema_screen_price < 1000000000)) 				{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000)/100)+" milion");}
    	else if ((cinema_screen_price >= 1000000000) && (cinema_screen_price < 1000000000000L))			{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000000)/100)+" billion");}
    	else if ((cinema_screen_price >= 1000000000000L) && (cinema_screen_price < 1000000000000000L))	{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000000000L)/100)+" trillion");} 
       
    	satellite_screen_amount = game_progress.getInt("satellite_screen_amount", 0); 
    	satellite_screen_price = game_progress.getLong("satellite_screen_price", 800000000L);
    	final TextView satellite_screen_amount_text = (TextView) findViewById(R.id.satellite_screen_amount_text);
    	satellite_screen_amount_text.setText("Amount: "+satellite_screen_amount);          
    	final TextView satellite_screen_price_text = (TextView) findViewById(R.id.satellite_screen_price_text);
    	if (satellite_screen_price < 1000000) 																{satellite_screen_price_text.setText("Price: "+satellite_screen_price);}                
    	else if ((satellite_screen_price >= 1000000) && (satellite_screen_price < 1000000000)) 				{satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000)/100)+" milion");}
    	else if ((satellite_screen_price >= 1000000000) && (satellite_screen_price < 1000000000000L))		{satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000000)/100)+" billion");}
    	else if ((satellite_screen_price >= 1000000000000L) && (satellite_screen_price < 1000000000000000L)){satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000000000L)/100)+" trillion");} 
    	    	
    	moon_screen_amount = game_progress.getInt("moon_screen_amount", 0); 
    	moon_screen_price = game_progress.getLong("moon_screen_price", 5000000000L);
    	final TextView moon_screen_amount_text = (TextView) findViewById(R.id.moon_screen_amount_text);
    	moon_screen_amount_text.setText("Amount: "+moon_screen_amount);          
    	final TextView moon_screen_price_text = (TextView) findViewById(R.id.moon_screen_price_text);
    	if (moon_screen_price < 1000000) 															{moon_screen_price_text.setText("Price: "+moon_screen_price);}                
    	else if ((moon_screen_price >= 1000000) && (moon_screen_price < 1000000000)) 				{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000)/100)+" milion");}
    	else if ((moon_screen_price >= 1000000000) && (moon_screen_price < 1000000000000L))			{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000000)/100)+" billion");}
    	else if ((moon_screen_price >= 1000000000000L) && (moon_screen_price < 1000000000000000L))	{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000000000L)/100)+" trillion");} 
    	    	
    	sun_screen_amount = game_progress.getInt("sun_screen_amount", 0); 
    	sun_screen_price = game_progress.getLong("sun_screen_price", 70000000000L);
    	final TextView sun_screen_amount_text = (TextView) findViewById(R.id.sun_screen_amount_text);
    	sun_screen_amount_text.setText("Amount: "+sun_screen_amount);          
    	final TextView sun_screen_price_text = (TextView) findViewById(R.id.sun_screen_price_text);
    	if (sun_screen_price < 1000000) 														{sun_screen_price_text.setText("Price: "+sun_screen_price);}                
    	else if ((sun_screen_price >= 1000000) && (sun_screen_price < 1000000000)) 				{sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000)/100)+" milion");}
    	else if ((sun_screen_price >= 1000000000) && (sun_screen_price < 1000000000000L))		{sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000000)/100)+" billion");}
    	else if ((sun_screen_price >= 1000000000000L) && (sun_screen_price < 1000000000000000L)){sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000000000L)/100)+" trillion");} 
    	  	
    	solar_system_screen_amount = game_progress.getInt("solar_system_screen_amount", 0); 
    	solar_system_screen_price = game_progress.getLong("solar_system_screen_price", 1000000000000L); 
    	final TextView solar_system_screen_amount_text = (TextView) findViewById(R.id.solar_system_screen_amount_text);
    	solar_system_screen_amount_text.setText("Amount: "+solar_system_screen_amount);         
    	final TextView solar_system_screen_price_text = (TextView) findViewById(R.id.solar_system_screen_price_text);
    	if (solar_system_screen_price < 1000000) 																	{solar_system_screen_price_text.setText("Price: "+solar_system_screen_price);}                
    	else if ((solar_system_screen_price >= 1000000) && (solar_system_screen_price < 1000000000)) 				{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000)/100)+" milion");}
    	else if ((solar_system_screen_price >= 1000000000) && (solar_system_screen_price < 1000000000000L))			{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000000)/100)+" billion");}
    	else if ((solar_system_screen_price >= 1000000000000L) && (solar_system_screen_price < 1000000000000000L))	{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000000000L)/100)+" trillion");}
    	   	
    	global_multiplier = game_progress.getFloat("global_multiplier", (float) 1.00);
    	final TextView global_multiplier_text = (TextView) findViewById(R.id.global_multiplier_text);
    	global_multiplier_text.setText("Global Multiplier: "+global_multiplier+"*PpS");   
    	   	
    	show_tutorial = game_progress.getBoolean("show_tutorial", true);
 
        //////////ZA£ADOWANIE ANIMACJI//////////   
        anim_fade_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        anim_zoom_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
    	
        new CountDownTimer(315576000, 100) { //////////TIMER dodaj¹cy 1/10 PpS co 1/10 sec. //////////       	
        	double			PpS_after_multiplers;
            public void onTick(long millisUntilFinished) {      	
            	pixels += (PpS*pps_multipler*global_multiplier)/10;
                
                if (pixels < 1000000) 													
                {pixels_amount_text.setText((Math.floor(pixels*10)/10)+"");}                
                
                else if ((pixels >= 1000000) && (pixels < 1000000000)) 					
                {pixels_amount_text.setText((Math.floor(pixels/10000)/100)+" milion");}
                
                else if ((pixels >= 1000000000) && (pixels < 1000000000000L))			
                {pixels_amount_text.setText((Math.floor(pixels/10000000)/100)+" billion");}
                
                else if ((pixels >= 1000000000000L) && (pixels < 1000000000000000L))	
                {pixels_amount_text.setText((Math.floor(pixels/10000000000L)/100)+" trillion");}  
                
                else if ((pixels >= 1000000000000000L) && (pixels < 1000000000000000000L))	
                {pixels_amount_text.setText((Math.floor(pixels/10000000000000L)/100)+" quadrillion");} 
                
                else if (pixels >= 1000000000000000000L)	
                {pixels_amount_text.setText((Math.floor(pixels/10000000000000000L)/100)+" quintillion");}
                              
                
                PpS_after_multiplers = PpS*pps_multipler*global_multiplier;              
                if (PpS_after_multiplers < 1000000) 																						
                {PpS_amount_text.setText((Math.floor(PpS_after_multiplers*100)/100)+"");}
                
                else if ((PpS_after_multiplers >= 1000000) && (PpS_after_multiplers < 1000000000))					
                {PpS_amount_text.setText((Math.floor(PpS_after_multiplers/10000)/100)+" milion");}
                
                else if ((PpS_after_multiplers >= 1000000000) && (PpS_after_multiplers < 1000000000000L))			
                {PpS_amount_text.setText((Math.floor(PpS_after_multiplers/10000000)/100)+" billion");}
                
                else if ((PpS_after_multiplers >= 1000000000000L) && (PpS_after_multiplers < 1000000000000000L))	
                {PpS_amount_text.setText((Math.floor(PpS_after_multiplers/10000000000L)/100)+" trillion");}
                
                else if (PpS_after_multiplers >= 1000000000000000L)	
                {PpS_amount_text.setText((Math.floor(PpS_after_multiplers/10000000000000L)/100)+" quadrillion");}
            }
            public void onFinish() {     
            }
         }.start();           
         
         new CountDownTimer(315576000, 60000) { //////////TIMER zapisuj¹cy stan gry co 60 sec //////////    
             public void onTick(long millisUntilFinished) {
            	 
            	 if (millisUntilFinished < 315566000) {
	            	 SharedPreferences game_progress = getSharedPreferences(PREFS_NAME, 0); 
	            	 SharedPreferences.Editor editor = game_progress.edit();
	        	    	
	            	 editor.putFloat("global_multipler", global_multiplier);   	
	            	 editor.putLong("pixels", (long) pixels);
	            	 editor.putLong("PpS", (long) PpS);
	
	            	 editor.putLong("tap_multipler", tap_multipler);
	            	 editor.putInt("tap_x2_amount", tap_x2_amount);
	            	 editor.putLong("tap_x2_price", tap_x2_price);
	        	    	
	            	 editor.putFloat("pps_multipler", pps_multipler);   	
	            	 editor.putInt("pps_up_amount", pps_up_amount);
	            	 editor.putLong("pps_up_price", pps_up_price);
	        	  	
	            	 editor.putInt("coursor_amount", coursor_amount);
	            	 editor.putLong("coursor_price", coursor_price);
		        	  
	            	 editor.putInt("calculator_amount", calculator_amount);
	            	 editor.putLong("calculator_price", calculator_price);          
		        	      
	            	 editor.putInt("cell_phone_amount", cell_phone_amount);
	            	 editor.putLong("cell_phone_price", cell_phone_price);        
		        	        
	            	 editor.putInt("smartphone_amount", smartphone_amount);
	            	 editor.putLong("smartphone_price", smartphone_price);       
		        	    
	            	 editor.putInt("tablet_amount", tablet_amount);
	            	 editor.putLong("tablet_price", tablet_price);    
		        	   
	            	 editor.putInt("monitor_amount", monitor_amount);
	            	 editor.putLong("monitor_price", monitor_price);     
		       	    
	            	 editor.putInt("television_amount", television_amount);
	            	 editor.putLong("television_price", television_price);      
		       	   
	            	 editor.putInt("cinema_screen_amount", cinema_screen_amount);
	            	 editor.putLong("cinema_screen_price",cinema_screen_price);                 
		       	 
	            	 editor.putInt("satellite_screen_amount", satellite_screen_amount);
	            	 editor.putLong("satellite_screen_price", satellite_screen_price); 
	            	 
	            	 editor.putInt("moon_screen_amount", moon_screen_amount);
	            	 editor.putLong("moon_screen_price", moon_screen_price); 
	            	 
	            	 editor.putInt("sun_screen_amount", sun_screen_amount);
	            	 editor.putLong("sun_screen_price", sun_screen_price); 
	            	 
	            	 editor.putInt("solar_system_screen_amount", solar_system_screen_amount);
	            	 editor.putLong("solar_system_screen_price", solar_system_screen_price);  
	            	 editor.commit();
	            	 
	            	 Toast.makeText(GameActivity.this,"Game Saved!",Toast.LENGTH_SHORT).show();   
            	 }         	             	 
             }             
             public void onFinish() {     
             }
          }.start();               
    }
    
    @Override
    protected void onResume(){   	
        super.onResume();
        //////////TUTORIAL//////////  
        if (show_tutorial == true) {
            final Dialog dialog = new Dialog(GameActivity.this);
            dialog.setContentView(R.layout.tutorial);
            dialog.setTitle("TUTORIAL");
            dialog.setCancelable(true);

            final ImageView got_it = (ImageView) dialog.findViewById(R.id.got_it);
            got_it.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                        dialog.cancel();
                    }
                }); 
            
            final ImageView dont_show = (ImageView) dialog.findViewById(R.id.dont_show);
            dont_show.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
	                	SharedPreferences game_progress = getSharedPreferences(PREFS_NAME, 0); 
	                	SharedPreferences.Editor editor = game_progress.edit();
	                	show_tutorial = false;
		            	editor.putBoolean("show_tutorial", show_tutorial); 
		            	editor.commit();
                        dialog.cancel();
                    }
                });
            
            dialog.show();	
        }       
    }
    
    //////////AKCJE PO WCIŒNIÊCIU DANEGO PRZYCISKU//////////   
    public void pixel_clicked(View v){
    	final TextView pixel_taped_text = (TextView) findViewById(R.id.pixel_taped_text);
    	if (tap_multipler < 1000000) 														{pixel_taped_text.setText("+"+tap_multipler);}                
    	else if ((tap_multipler >= 1000000) && (tap_multipler < 1000000000)) 				{pixel_taped_text.setText("+"+(Math.floor(tap_multipler/10000)/100)+" milion");}
    	else if ((tap_multipler >= 1000000000) && (tap_multipler < 1000000000000L))			{pixel_taped_text.setText("+"+(Math.floor(tap_multipler/10000000)/100)+" billion");}
    	else if ((tap_multipler >= 1000000000000L) && (tap_multipler < 1000000000000000L))	{pixel_taped_text.setText("+"+(Math.floor(tap_multipler/10000000000L)/100)+" trillion");} 
    	final ImageView pixel_image = (ImageView) findViewById(R.id.pixel_image);
    	pixels+=tap_multipler;    
    	pixel_taped_text.setTextColor(Color.argb(255, 255, 255, 255));
        pixel_taped_text.startAnimation(anim_fade_out);
        pixel_image.startAnimation(anim_zoom_in);
  	}
    
    public void save_clicked(View v){ 
		final ImageView save_image = (ImageView) findViewById(R.id.save_image);
		SharedPreferences game_progress = getSharedPreferences(PREFS_NAME, 0); 
    	SharedPreferences.Editor editor = game_progress.edit();
    	
    	editor.putFloat("global_multipler", global_multiplier);   	
    	editor.putLong("pixels", (long) pixels);
    	editor.putLong("PpS", (long) PpS);

    	editor.putLong("tap_multipler", tap_multipler);
    	editor.putInt("tap_x2_amount", tap_x2_amount);
    	editor.putLong("tap_x2_price", tap_x2_price);
    	
    	editor.putFloat("pps_multipler", pps_multipler);   	
    	editor.putInt("pps_up_amount", pps_up_amount);
    	editor.putLong("pps_up_price", pps_up_price);
  	 
    	editor.putInt("coursor_amount", coursor_amount);
    	editor.putLong("coursor_price", coursor_price);
  
    	editor.putInt("calculator_amount", calculator_amount);
    	editor.putLong("calculator_price", calculator_price);             
    	  
    	editor.putInt("cell_phone_amount", cell_phone_amount);
    	editor.putLong("cell_phone_price", cell_phone_price);        
    	    
    	editor.putInt("smartphone_amount", smartphone_amount);
    	editor.putLong("smartphone_price", smartphone_price);       
     
    	editor.putInt("tablet_amount", tablet_amount);
    	editor.putLong("tablet_price", tablet_price);    
   
    	editor.putInt("monitor_amount", monitor_amount);
    	editor.putLong("monitor_price", monitor_price);     
    
    	editor.putInt("television_amount", television_amount);
    	editor.putLong("television_price", television_price);      
   
    	editor.putInt("cinema_screen_amount", cinema_screen_amount);
    	editor.putLong("cinema_screen_price",cinema_screen_price);                
 
    	editor.putInt("satellite_screen_amount", satellite_screen_amount);
    	editor.putLong("satellite_screen_price", satellite_screen_price); 
    	
	   	editor.putInt("moon_screen_amount", moon_screen_amount);
	   	editor.putLong("moon_screen_price", moon_screen_price); 
	   	 
	   	editor.putInt("sun_screen_amount", sun_screen_amount);
	   	editor.putLong("sun_screen_price", sun_screen_price); 
	   	 
	   	editor.putInt("solar_system_screen_amount", solar_system_screen_amount);
	   	editor.putLong("solar_system_screen_price", solar_system_screen_price); 
	   	editor.commit();
    	
   	 	Toast.makeText(GameActivity.this,"Game Saved!",Toast.LENGTH_SHORT).show();    
    	
    	save_image.startAnimation(anim_zoom_in);
  	}

    public void tap_x2_clicked(View v){  
    	final TextView tap_x2_price_text = (TextView) findViewById(R.id.tap_x2_price_text);
        final TextView tap_x2_amount_text = (TextView) findViewById(R.id.tap_x2_amount_text);
        final ImageView tap_x2_image = (ImageView) findViewById(R.id.tap_x2_image);
    	if (pixels >= tap_x2_price)
        {
	        pixels-=tap_x2_price;
	    	tap_multipler=tap_multipler*2;
    		tap_x2_amount+=1;
	        tap_x2_amount_text.setText("A: "+tap_x2_amount); 
	        tap_x2_price = (long) (tap_x2_price * 2.1 + 800);
	        if (tap_x2_price < 1000000) 													{tap_x2_price_text.setText("P: "+tap_x2_price);}                
	    	else if ((tap_x2_price >= 1000000) && (tap_x2_price < 1000000000)) 				{tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000)/100)+" milion");}
	    	else if ((tap_x2_price >= 1000000000) && (tap_x2_price < 1000000000000L))		{tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000000)/100)+" billion");}
	    	else if ((tap_x2_price >= 1000000000000L) && (tap_x2_price < 1000000000000000L)){tap_x2_price_text.setText("P: "+(Math.floor(tap_x2_price/10000000000L)/100)+" trillion");} 
	        
	        tap_x2_image.startAnimation(anim_zoom_in); 	
        }
  	}
    
    public void pps_up_clicked(View v){ 
    	final TextView pps_up_price_text = (TextView) findViewById(R.id.pps_up_price_text);	
    	final TextView pps_up_amount_text = (TextView) findViewById(R.id.pps_up_amount_text);
        final ImageView pps_up_image = (ImageView) findViewById(R.id.pps_up_image);
    	if (pixels >= pps_up_price)
        {
	        pixels-=pps_up_price;
    		pps_multipler+=0.01;
    		pps_up_amount+=1;
	    	pps_up_amount_text.setText("A: "+pps_up_amount);
	    	pps_up_price = (long) (pps_up_price * 1.05 + 200);
	    	if (pps_up_price < 1000000) 													{pps_up_price_text.setText("P: "+pps_up_price);}                
	    	else if ((pps_up_price >= 1000000) && (pps_up_price < 1000000000)) 				{pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000)/100)+" milion");}
	    	else if ((pps_up_price >= 1000000000) && (pps_up_price < 1000000000000L))		{pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000000)/100)+" billion");}
	    	else if ((pps_up_price >= 1000000000000L) && (pps_up_price < 1000000000000000L)){pps_up_price_text.setText("P: "+(Math.floor(pps_up_price/10000000000L)/100)+" trillion");} 
	    	pps_up_image.startAnimation(anim_zoom_in);
        }
  	}
    
    public void coursor_clicked(View v){  
    	final TextView coursor_price_text = (TextView) findViewById(R.id.coursor_price_text);
        final TextView coursor_amount_text = (TextView) findViewById(R.id.coursor_amount_text);
        final ImageView coursor_image = (ImageView) findViewById(R.id.coursor_image);
    	if (pixels >= coursor_price)
        {
	        pixels-=coursor_price;
	        PpS+=coursor;
	        coursor_amount+=1;
	    	coursor_amount_text.setText("Amount: "+coursor_amount);
	        coursor_price = (long) (coursor_price * 1.15);
	    	if (coursor_price < 1000000) 														{coursor_price_text.setText("Price: "+coursor_price);}                
	    	else if ((coursor_price >= 1000000) && (coursor_price < 1000000000)) 				{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000)/100)+" milion");}
	    	else if ((coursor_price >= 1000000000) && (coursor_price < 1000000000000L))   		{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000000)/100)+" billion");}
	    	else if ((coursor_price >= 1000000000000L) && (coursor_price < 1000000000000000L))	{coursor_price_text.setText("Price: "+(Math.floor(coursor_price/10000000000L)/100)+" trillion");} 
	        coursor_image.startAnimation(anim_zoom_in);
        }
  	}
    
    public void calculator_clicked(View v){  
        final TextView calculator_price_text = (TextView) findViewById(R.id.calculator_price_text);
    	final TextView calculator_amount_text = (TextView) findViewById(R.id.calculator_amount_text);
        final ImageView calculator_image = (ImageView) findViewById(R.id.calculator_image);
    	if (pixels >= calculator_price)
        {
	        pixels-=calculator_price;
	        PpS+=calculator;
	    	calculator_amount+=1;
	    	calculator_amount_text.setText("Amount: "+calculator_amount);  
	        calculator_price = (long) (calculator_price * 1.15);
	    	if (calculator_price < 1000000) 														{calculator_price_text.setText("Price: "+calculator_price);}                
	    	else if ((calculator_price >= 1000000) && (calculator_price < 1000000000)) 				{calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000)/100)+" milion");}
	    	else if ((calculator_price >= 1000000000) && (calculator_price < 1000000000000L))		{calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000000)/100)+" billion");}
	    	else if ((calculator_price >= 1000000000000L) && (calculator_price < 1000000000000000L)){calculator_price_text.setText("Price: "+(Math.floor(calculator_price/10000000000L)/100)+" trillion");} 
	    	calculator_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void cell_phone_clicked(View v){  
        final TextView cell_phone_price_text = (TextView) findViewById(R.id.cell_phone_price_text);
    	final TextView cell_phone_amount_text = (TextView) findViewById(R.id.cell_phone_amount_text);
        final ImageView cell_phone_image = (ImageView) findViewById(R.id.cell_phone_image);
    	if (pixels >= cell_phone_price)
        {
	        pixels-=cell_phone_price;
	        PpS+=cell_phone;
	    	cell_phone_amount+=1;
	    	cell_phone_amount_text.setText("Amount: "+cell_phone_amount);
	        cell_phone_price = (long) (cell_phone_price * 1.15);
	    	if (cell_phone_price < 1000000) 														{cell_phone_price_text.setText("Price: "+cell_phone_price);}                
	    	else if ((cell_phone_price >= 1000000) && (cell_phone_price < 1000000000)) 				{cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000)/100)+" milion");}
	    	else if ((cell_phone_price >= 1000000000) && (cell_phone_price < 1000000000000L))		{cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000000)/100)+" billion");}
	    	else if ((cell_phone_price >= 1000000000000L) && (cell_phone_price < 1000000000000000L)){cell_phone_price_text.setText("Price: "+(Math.floor(cell_phone_price/10000000000L)/100)+" trillion");} 
	    	cell_phone_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void smartphone_clicked(View v){
        final TextView smartphone_price_text = (TextView) findViewById(R.id.smartphone_price_text); 
    	final TextView smartphone_amount_text = (TextView) findViewById(R.id.smartphone_amount_text);
        final ImageView smartphone_image = (ImageView) findViewById(R.id.smartphone_image);
    	if (pixels >= smartphone_price)
        {
	        pixels-=smartphone_price;
	        PpS+=smartphone;
	    	smartphone_amount+=1;
	    	smartphone_amount_text.setText("Amount: "+smartphone_amount);
	        smartphone_price = (long) (smartphone_price * 1.15);
	    	if (smartphone_price < 1000000) 														{smartphone_price_text.setText("Price: "+smartphone_price);}                
	    	else if ((smartphone_price >= 1000000) && (smartphone_price < 1000000000)) 				{smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000)/100)+" milion");}
	    	else if ((smartphone_price >= 1000000000) && (smartphone_price < 1000000000000L))		{smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000000)/100)+" billion");}
	    	else if ((smartphone_price >= 1000000000000L) && (smartphone_price < 1000000000000000L)){smartphone_price_text.setText("Price: "+(Math.floor(smartphone_price/10000000000L)/100)+" trillion");} 
	        smartphone_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void tablet_clicked(View v){  
        final TextView tablet_price_text = (TextView) findViewById(R.id.tablet_price_text);   
    	final TextView tablet_amount_text = (TextView) findViewById(R.id.tablet_amount_text);
        final ImageView tablet_image = (ImageView) findViewById(R.id.tablet_image);
    	if (pixels >= tablet_price)
        {
	        pixels-=tablet_price;
	        PpS+=tablet;
	    	tablet_amount+=1;
	    	tablet_amount_text.setText("Amount: "+tablet_amount);
	        tablet_price = (long) (tablet_price * 1.15); 
	    	if (tablet_price < 1000000) 													{tablet_price_text.setText("Price: "+tablet_price);}                
	    	else if ((tablet_price >= 1000000) && (tablet_price < 1000000000)) 				{tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000)/100)+" milion");}
	    	else if ((tablet_price >= 1000000000) && (tablet_price < 1000000000000L))		{tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000000)/100)+" billion");}
	    	else if ((tablet_price >= 1000000000000L) && (tablet_price < 1000000000000000L)){tablet_price_text.setText("Price: "+(Math.floor(tablet_price/10000000000L)/100)+" trillion");} 
	        tablet_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void monitor_clicked(View v){  
        final TextView monitor_price_text = (TextView) findViewById(R.id.monitor_price_text);
    	final TextView monitor_amount_text = (TextView) findViewById(R.id.monitor_amount_text);
        final ImageView monitor_image = (ImageView) findViewById(R.id.monitor_image);
    	if (pixels >= monitor_price)
        {
	        pixels-=monitor_price;
	        PpS+=monitor;
	    	monitor_amount+=1; 
	    	monitor_amount_text.setText("Amount: "+monitor_amount);
	        monitor_price = (long) (monitor_price * 1.15);
	    	if (monitor_price < 1000000) 														{monitor_price_text.setText("Price: "+monitor_price);}                
	    	else if ((monitor_price >= 1000000) && (monitor_price < 1000000000)) 				{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000)/100)+" milion");}
	    	else if ((monitor_price >= 1000000000) && (monitor_price < 1000000000000L))			{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000000)/100)+" billion");}
	    	else if ((monitor_price >= 1000000000000L) && (monitor_price < 1000000000000000L))	{monitor_price_text.setText("Price: "+(Math.floor(monitor_price/10000000000L)/100)+" trillion");} 
	        monitor_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void television_clicked(View v){ 
        final TextView television_price_text = (TextView) findViewById(R.id.television_price_text);
    	final TextView television_amount_text = (TextView) findViewById(R.id.television_amount_text);
        final ImageView television_image = (ImageView) findViewById(R.id.television_image);
    	if (pixels >= television_price)
	    {
	        pixels-=television_price;
	        PpS+=television;
	    	television_amount+=1;
	    	television_amount_text.setText("Amount: "+television_amount);
	        television_price = (long) (television_price * 1.15);    
	    	if (television_price < 1000000) 														{television_price_text.setText("Price: "+television_price);}                
	    	else if ((television_price >= 1000000) && (television_price < 1000000000)) 				{television_price_text.setText("Price: "+(Math.floor(television_price/10000)/100)+" milion");}
	    	else if ((television_price >= 1000000000) && (television_price < 1000000000000L))		{television_price_text.setText("Price: "+(Math.floor(television_price/10000000)/100)+" billion");}
	    	else if ((television_price >= 1000000000000L) && (television_price < 1000000000000000L)){television_price_text.setText("Price: "+(Math.floor(television_price/10000000000L)/100)+" trillion");} 
	        television_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void cinema_screen_clicked(View v){  
        final TextView cinema_screen_price_text = (TextView) findViewById(R.id.cinema_screen_price_text);
    	final TextView cinema_screen_amount_text = (TextView) findViewById(R.id.cinema_screen_amount_text);
        final ImageView cinema_screen_image = (ImageView) findViewById(R.id.cinema_screen_image);
    	if (pixels >= cinema_screen_price)
        {
	        pixels-=cinema_screen_price;
	        PpS+=cinema_screen;
	    	cinema_screen_amount+=1;
	    	cinema_screen_amount_text.setText("Amount: "+cinema_screen_amount); 
	        cinema_screen_price = (long) (cinema_screen_price * 1.15);  
	    	if (cinema_screen_price < 1000000) 																{cinema_screen_price_text.setText("Price: "+cinema_screen_price);}                
	    	else if ((cinema_screen_price >= 1000000) && (cinema_screen_price < 1000000000)) 				{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000)/100)+" milion");}
	    	else if ((cinema_screen_price >= 1000000000) && (cinema_screen_price < 1000000000000L))			{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000000)/100)+" billion");}
	    	else if ((cinema_screen_price >= 1000000000000L) && (cinema_screen_price < 1000000000000000L))	{cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/10000000000L)/100)+" trillion");} 
	        cinema_screen_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    public void satellite_screen_clicked(View v){  
        final TextView satellite_screen_price_text = (TextView) findViewById(R.id.satellite_screen_price_text);
    	final TextView satellite_screen_amount_text = (TextView) findViewById(R.id.satellite_screen_amount_text);
        final ImageView satellite_screen_image = (ImageView) findViewById(R.id.satellite_screen_image);
    	if (pixels >= satellite_screen_price)
        {
	        pixels-=satellite_screen_price;
	        PpS+=satellite_screen;
    		satellite_screen_amount+=1;
	    	satellite_screen_amount_text.setText("Amount: "+satellite_screen_amount);
	        satellite_screen_price = (long) (satellite_screen_price * 1.15); 
	    	if (satellite_screen_price < 1000000) 																{satellite_screen_price_text.setText("Price: "+satellite_screen_price);}                
	    	else if ((satellite_screen_price >= 1000000) && (satellite_screen_price < 1000000000)) 				{satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000)/100)+" milion");}
	    	else if ((satellite_screen_price >= 1000000000) && (satellite_screen_price < 1000000000000L))		{satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000000)/100)+" billion");}
	    	else if ((satellite_screen_price >= 1000000000000L) && (satellite_screen_price < 1000000000000000L)){satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/10000000000L)/100)+" trillion");} 
	    	satellite_screen_image.startAnimation(anim_zoom_in);
        }
  	}    
    
    public void moon_screen_clicked(View v){  
        final TextView moon_screen_price_text = (TextView) findViewById(R.id.moon_screen_price_text);
    	final TextView moon_screen_amount_text = (TextView) findViewById(R.id.moon_screen_amount_text);
        final ImageView moon_screen_image = (ImageView) findViewById(R.id.moon_screen_image);
    	if (pixels >= moon_screen_price)
        {
	        pixels-=moon_screen_price;
	        PpS+=moon_screen;
    		moon_screen_amount+=1;
	        moon_screen_amount_text.setText("Amount: "+moon_screen_amount);
	        moon_screen_price = (long) (moon_screen_price * 1.15); 
	        if (moon_screen_price < 1000000) 															{moon_screen_price_text.setText("Price: "+moon_screen_price);}                
	    	else if ((moon_screen_price >= 1000000) && (moon_screen_price < 1000000000)) 				{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000)/100)+" milion");}
	    	else if ((moon_screen_price >= 1000000000) && (moon_screen_price < 1000000000000L))			{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000000)/100)+" billion");}
	    	else if ((moon_screen_price >= 1000000000000L) && (moon_screen_price < 1000000000000000L))	{moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/10000000000L)/100)+" trillion");} 
	        moon_screen_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    
    public void sun_screen_clicked(View v){  
        final TextView sun_screen_price_text = (TextView) findViewById(R.id.sun_screen_price_text);
    	final TextView sun_screen_amount_text = (TextView) findViewById(R.id.sun_screen_amount_text);
        final ImageView sun_screen_image = (ImageView) findViewById(R.id.sun_screen_image);
    	if (pixels >= sun_screen_price)
        {
	        pixels-=sun_screen_price;
	        PpS+=sun_screen;
    		sun_screen_amount+=1;
	        sun_screen_amount_text.setText("Amount: "+sun_screen_amount);
	        sun_screen_price = (long) (sun_screen_price * 1.15); 
	        if (sun_screen_price < 1000000) 														{sun_screen_price_text.setText("Price: "+sun_screen_price);}                
	    	else if ((sun_screen_price >= 1000000) && (sun_screen_price < 1000000000)) 				{sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000)/100)+" milion");}
	    	else if ((sun_screen_price >= 1000000000) && (sun_screen_price < 1000000000000L))		{sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000000)/100)+" billion");}
	    	else if ((sun_screen_price >= 1000000000000L) && (sun_screen_price < 1000000000000000L)){sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/10000000000L)/100)+" trillion");} 
	        sun_screen_image.startAnimation(anim_zoom_in);
        }
  	} 
    
    
    public void solar_system_screen_clicked(View v){  
        final TextView solar_system_screen_price_text = (TextView) findViewById(R.id.solar_system_screen_price_text);
    	final TextView solar_system_screen_amount_text = (TextView) findViewById(R.id.solar_system_screen_amount_text);
        final ImageView solar_system_screen_image = (ImageView) findViewById(R.id.solar_system_screen_image);
    	if (pixels >= solar_system_screen_price)
        {
	        pixels-=solar_system_screen_price;
	        PpS+=solar_system_screen;
    		solar_system_screen_amount+=1;
	        solar_system_screen_amount_text.setText("Amount: "+solar_system_screen_amount);
	        solar_system_screen_price = (long) (solar_system_screen_price * 1.15); 
	        if (solar_system_screen_price < 1000000) 																	{solar_system_screen_price_text.setText("Price: "+solar_system_screen_price);}                
	    	else if ((solar_system_screen_price >= 1000000) && (solar_system_screen_price < 1000000000)) 				{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000)/100)+" milion");}
	    	else if ((solar_system_screen_price >= 1000000000) && (solar_system_screen_price < 1000000000000L))			{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000000)/100)+" billion");}
	    	else if ((solar_system_screen_price >= 1000000000000L) && (solar_system_screen_price < 1000000000000000L))	{solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/10000000000L)/100)+" trillion");} 
	        solar_system_screen_image.startAnimation(anim_zoom_in);
        }
  	}   
    
    public void reset_clicked(View v){ 
    	new AlertDialog.Builder(this)
    		.setIcon(android.R.drawable.ic_dialog_alert)
    		.setTitle("Reseting Activity")
    		.setMessage("Are you sure you want to reset game?"+"\n"+"\n"+"(Each 1 billion pixels is +0.01 to Global Multipler)")
    		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	final ImageView save_image = (ImageView) findViewById(R.id.reset_image);
            		SharedPreferences game_progress = getSharedPreferences(PREFS_NAME, 0); 
                	SharedPreferences.Editor editor = game_progress.edit();
               	
                	global_multiplier += Math.floor((int) (pixels/1000000000)) * 0.01;
                	
                	pixels = (double) 0L; 
                    PpS = (double) 0L;

                    tap_multipler = 1; 
                    tap_x2_amount = 0; 
                    tap_x2_price = 100L; 
                    final TextView pixel_taped_text = (TextView) findViewById(R.id.pixel_taped_text);
                	pixel_taped_text.setText("+"+tap_multipler); 
                	final TextView tap_x2_amount_text = (TextView) findViewById(R.id.tap_x2_amount_text);
                	tap_x2_amount_text.setText("A: "+tap_x2_amount);      	
                	final TextView tap_x2_price_text = (TextView) findViewById(R.id.tap_x2_price_text);
            	    tap_x2_price_text.setText(""+tap_x2_price);     	

                    pps_multipler = (float) 1.00;
                    pps_up_amount = 0; 
                    pps_up_price = 100L;
            	    final TextView pps_up_amount_text = (TextView) findViewById(R.id.pps_up_amount_text);
                	pps_up_amount_text.setText("A: "+pps_up_amount);
                	final TextView pps_up_price_text = (TextView) findViewById(R.id.pps_up_price_text);
            	    pps_up_price_text.setText(""+pps_up_price);
                    
                    coursor_amount = 0;
                    coursor_price = 15L;
                	final TextView coursor_amount_text = (TextView) findViewById(R.id.coursor_amount_text);
                	coursor_amount_text.setText("Amount: "+coursor_amount);
                	final TextView coursor_price_text = (TextView) findViewById(R.id.coursor_price_text);
                	coursor_price_text.setText("Price: "+coursor_price);
             
                    calculator_amount = 0; 
                    calculator_price = 500L; 
                	final TextView calculator_amount_text = (TextView) findViewById(R.id.calculator_amount_text);
                	calculator_amount_text.setText("Amount: "+calculator_amount);   
                	final TextView calculator_price_text = (TextView) findViewById(R.id.calculator_price_text);
                	calculator_price_text.setText("Price: "+calculator_price);
             
                    cell_phone_amount = 0; 
                    cell_phone_price = 4000L; 
                	final TextView cell_phone_amount_text = (TextView) findViewById(R.id.cell_phone_amount_text);
                	cell_phone_amount_text.setText("Amount: "+cell_phone_amount);
                	final TextView cell_phone_price_text = (TextView) findViewById(R.id.cell_phone_price_text);
                	cell_phone_price_text.setText("Price: "+cell_phone_price);
             
                    smartphone_amount = 0; 
                    smartphone_price = 50000L; 
                	final TextView smartphone_amount_text = (TextView) findViewById(R.id.smartphone_amount_text);
                	smartphone_amount_text.setText("Amount: "+smartphone_amount);
                	final TextView smartphone_price_text = (TextView) findViewById(R.id.smartphone_price_text);
                	smartphone_price_text.setText("Price: "+smartphone_price); 

                    tablet_amount = 0; 
                    tablet_price = 400000L;     
                	final TextView tablet_amount_text = (TextView) findViewById(R.id.tablet_amount_text);
                	tablet_amount_text.setText("Amount: "+tablet_amount);
                	final TextView tablet_price_text = (TextView) findViewById(R.id.tablet_price_text);
                	tablet_price_text.setText("Price: "+tablet_price);
               
                    monitor_amount = 0; 
                    monitor_price = 2500000L; 
                	final TextView monitor_amount_text = (TextView) findViewById(R.id.monitor_amount_text);
                	monitor_amount_text.setText("Amount: "+monitor_amount);
                	final TextView monitor_price_text = (TextView) findViewById(R.id.monitor_price_text);
                	monitor_price_text.setText("Price: "+(Math.floor(monitor_price/100000)/10)+" milion");  
             
                    television_amount = 0; 
                    television_price = 20000000L; 
                	final TextView television_amount_text = (TextView) findViewById(R.id.television_amount_text);
                	television_amount_text.setText("Amount: "+television_amount); 
                	final TextView television_price_text = (TextView) findViewById(R.id.television_price_text);
                	television_price_text.setText("Price: "+(Math.floor(television_price/100000)/10)+" milion");      
               
                	cinema_screen_amount = 0; 
                	cinema_screen_price = 150000000L;
                	final TextView cinema_screen_amount_text = (TextView) findViewById(R.id.cinema_screen_amount_text);
                	cinema_screen_amount_text.setText("Amount: "+cinema_screen_amount); 
                	final TextView cinema_screen_price_text = (TextView) findViewById(R.id.cinema_screen_price_text);
                	cinema_screen_price_text.setText("Price: "+(Math.floor(cinema_screen_price/100000)/10)+" milion");          

                	satellite_screen_amount = 0; 
                	satellite_screen_price = 800000000L;   
                	final TextView satellite_screen_amount_text = (TextView) findViewById(R.id.satellite_screen_amount_text);
                	satellite_screen_amount_text.setText("Amount: "+satellite_screen_amount);        
                	final TextView satellite_screen_price_text = (TextView) findViewById(R.id.satellite_screen_price_text);
                	satellite_screen_price_text.setText("Price: "+(Math.floor(satellite_screen_price/100000)/10)+" milion"); 
                	
                	moon_screen_amount = 0; 
                	moon_screen_price = 5000000000L;       
                	final TextView moon_screen_amount_text = (TextView) findViewById(R.id.moon_screen_amount_text);
                	moon_screen_amount_text.setText("Amount: "+moon_screen_amount);   
                	final TextView moon_screen_price_text = (TextView) findViewById(R.id.moon_screen_price_text);
                	moon_screen_price_text.setText("Price: "+(Math.floor(moon_screen_price/100000000)/10)+" billion");
                	
                	sun_screen_amount = 0; 
                	sun_screen_price = 70000000000L;   
                	final TextView sun_screen_amount_text = (TextView) findViewById(R.id.sun_screen_amount_text);
                	sun_screen_amount_text.setText("Amount: "+sun_screen_amount);       
                	final TextView sun_screen_price_text = (TextView) findViewById(R.id.sun_screen_price_text);
                	sun_screen_price_text.setText("Price: "+(Math.floor(sun_screen_price/100000000)/10)+" billion"); 
                	
                	solar_system_screen_amount = 0; 
                	solar_system_screen_price = 1000000000000L;   
                	final TextView solar_system_screen_amount_text = (TextView) findViewById(R.id.solar_system_screen_amount_text);
                	solar_system_screen_amount_text.setText("Amount: "+solar_system_screen_amount);     
                	final TextView solar_system_screen_price_text = (TextView) findViewById(R.id.solar_system_screen_price_text);
                	solar_system_screen_price_text.setText("Price: "+(Math.floor(solar_system_screen_price/100000000000L)/10)+" trillion");
                	editor.commit();
                	
                	final TextView global_multiplier_text = (TextView) findViewById(R.id.global_multiplier_text);
                	global_multiplier_text.setText("Global Multiplier: "+global_multiplier+"*PpS");   
                	
               	 	Toast.makeText(GameActivity.this,"Game Reset!",Toast.LENGTH_SHORT).show();    
                	
                	save_image.startAnimation(anim_zoom_in);     
                }
            })
            .setNegativeButton("No", null)
            .show();
    	}
    
    private Toast exit_confirm;
    private long lastBackPressTime = 0;
    public void onBackPressed() {
    	if (this.lastBackPressTime < System.currentTimeMillis() - 3500) {
    		exit_confirm = Toast.makeText(this, "Press back again to close this app", Toast.LENGTH_LONG);
    		exit_confirm.show();
    		this.lastBackPressTime = System.currentTimeMillis();
    	} 
    	else {
    		if (exit_confirm != null) {
    			exit_confirm.cancel();
    		}
    	super.onBackPressed();
    	}   
    }
    
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
