package com.kurotkin;

import jssc.*;

/**
 * Created by Андрей on 29.06.2017.
 */
public class ComPort {
    private static SerialPort serialPort;
    private static EventListener serialPortListener;
    private int bSize;
    public int fl;

    public ComPort(int bSize) {
        fl = 0;
        this.bSize = bSize;
        String portName = getPort();
        serialPortListener = new EventListener();
        serialPort = new SerialPort(portName);
        System.out.println("Start serial port " + getPort());
        try {
            serialPort.openPort ();
            serialPort.setParams (SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setEventsMask (SerialPort.MASK_RXCHAR);
            serialPort.addEventListener (serialPortListener);
        }
        catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }

    private static String getPort() {
        String[] portNames = SerialPortList.getPortNames();
        if(portNames.length == 1) {
            return portNames[0];
        } else {
            return Settings.portName;
        }
    }

    public boolean getResult (){
        if(fl == 1) {
            return true;
        } else
            return false;
    }

    public String getFromSerial(){
        return serialPortListener.dataFromSerial;
    }

    private class EventListener implements SerialPortEventListener {
        String dataFromSerial = "";
        public void serialEvent (SerialPortEvent event)  {
            if (event.isRXCHAR () && event.getEventValue () > bSize){
                try {
                    String data = serialPort.readString (event.getEventValue ());
                    dataFromSerial += data;
                    System.out.println(data);
                    fl = 1;
                }
                catch (SerialPortException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
