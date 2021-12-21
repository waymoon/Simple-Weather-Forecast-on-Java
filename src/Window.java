import javax.swing.*;
import org.json.*;
import java.awt.*;
import java.awt.event.*;


public class Window {
	String KEY = "yourKeyHere";
	String result;
	
	// Window
	JFrame frame;
	JLabel label;
	JTextField textField = new JTextField(15);
	JButton button = new JButton();
	JLabel labeltemp;
	JLabel labeldesc;
	JLabel labelwind;
	JLabel labelhumidity;
	
	String textFieldValue;
	
	// Set font
	Font myFont = new Font("Verdana", Font.PLAIN, 15);
	
	Window() {
		// Frame methods
		frame = new JFrame("Weather");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 350);
		frame.setLayout(null);
		frame.setResizable(false);
		
		// Enter label methods
		label = new JLabel("Enter the city below: ");
		label.setBounds(10, 10, 200, 15);
		label.setFont(myFont);
		
		// Enter text field methods
		textField = new JTextField();
		textField.setBounds(10, 35, 265, 30);
		textField.setFont(myFont);
		
		// Button methods
		button = new JButton("Find");
		button.setBounds(10, 80, 265, 30);
		button.setFont(myFont);
		
		// Temperature label methods
		labeltemp = new JLabel();
		labeltemp.setBounds(10, 120, 200, 15);
		labeltemp.setFont(myFont);
		
		// Weather description label methods
		labeldesc = new JLabel();
		labeldesc.setBounds(10, 140, 265, 15);
		labeldesc.setFont(myFont);
		
		// Wind label methods
		labelwind = new JLabel();
		labelwind.setBounds(10, 160, 200, 15);
		labelwind.setFont(myFont);
		
		// Humidity label methods
		labelhumidity = new JLabel();
		labelhumidity.setBounds(10, 180, 200, 15);
		labelhumidity.setFont(myFont);
		
		// Adding objects to frame
		frame.add(label);
		frame.add(textField);
		frame.add(button);
		frame.add(labeltemp);
		frame.add(labeldesc);
		frame.add(labelwind);
		frame.add(labelhumidity);
		
		// Frame visibility is true
		frame.setVisible(true);
	}
	
	void SendAPI() {
		// Button action listener on press 
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				// Get content (city) from text field 
				String content = textField.getText();
				// Get string of json type data from other class
				String result = APIActions.GetData("http://api.openweathermap.org/data/2.5/weather?q=" + content + "&appid=" + KEY);				
				
				// Using JSON library create JSON object and pass json string 
				JSONObject obj = new JSONObject(result);
				// Take integer data type of temperature from main json object and convert to celsius from kelvins
				int temp = (int) (obj.getJSONObject("main").getInt("temp") - 272.15);
				int hum = (int) (obj.getJSONObject("main").getInt("humidity"));
				String temperature = String.valueOf(temp);
				
				// Using JSON library create JSON array type and pass json wether array from json
				JSONArray arr = obj.getJSONArray("weather");
				// Using JSON library create JSON object and pass weather array
				JSONObject jObj = arr.getJSONObject(0);
				// decription string to finally get wather description from weather array
				String desc = jObj.getString("description");
				// Take integer data type of speed from wind json object
				int wind = obj.getJSONObject("wind").getInt("speed");
				
				// Set text to labels which we created upward
				labeltemp.setText("Temperature: " + temperature + " °C");
				labeldesc.setText("Description: " + desc);
				labelwind.setText("Wind speed: " + wind + " m/s");
				labelhumidity.setText("Humidity: " + hum + "%");	
			}

		});
	}
		
}

	
