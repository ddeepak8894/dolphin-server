#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

const char* ssid = "DIGISOL";
const char* password = "qas1725utl1";
const String serverName = "http://3.111.108.14:4000/api/sensorDataStore/writeSensorData";
const int ledPin = 13;

const unsigned long timerDelay = 15000; // 15 seconds
unsigned long lastTime = 0;

void setup() {
  Serial.begin(115200);
  pinMode(ledPin, OUTPUT);
  
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
}

void loop() {
  unsigned long currentTime = millis();

  if (currentTime - lastTime >= timerDelay) {
    if (WiFi.status() == WL_CONNECTED) {
      WiFiClient client;
      HTTPClient http;

      // Prepare JSON payload
      const size_t bufferSize = JSON_OBJECT_SIZE(2);
      DynamicJsonDocument jsonBuffer(bufferSize);
      jsonBuffer["name_of_sensor"] = "krushna";
      jsonBuffer["data"] = random(0, 10000); // Generate a random value between 0 and 10000

      // Serialize JSON to a string
      String payload;
      serializeJson(jsonBuffer, payload);

      // Make HTTP POST request
      http.begin(client, serverName);
      http.addHeader("Content-Type", "application/json");

      int httpResponseCode = http.POST(payload);

      if (httpResponseCode > 0) {
        Serial.print("HTTP Response code: ");
        Serial.println(httpResponseCode);
        String response = http.getString();
        Serial.println(response);
        
        // Blink the LED on pin 13 for visual indication
        digitalWrite(ledPin, HIGH);
        delay(500);
        digitalWrite(ledPin, LOW);
      }
      else {
        Serial.print("Error code: ");
        Serial.println(httpResponseCode);
      }

      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }

    lastTime = currentTime;
  }
}
