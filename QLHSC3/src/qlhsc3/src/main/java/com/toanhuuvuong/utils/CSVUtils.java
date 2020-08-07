package com.toanhuuvuong.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.toanhuuvuong.converter.IConverter;

public class CSVUtils 
{
	private static final char DEFAULT_SEPARATOR = ',';
	
	private static String format(String str)
	{
		String result = str;
		
		if(str.contains("\""))
			result.replace("\"", "\"\"");
		
		return result;
	}
	private static void writeLine(Writer writer, List<String> row) throws IOException
	{
		boolean first = true;
		
		StringBuilder sb = new StringBuilder();
		for(String col : row)
		{
			if(!first)
				sb.append(DEFAULT_SEPARATOR);
			sb.append("\"" + format(col) + "\"");
			first = false;
		}
		sb.append(String.format("%n"));
		
		writer.append(sb.toString());
	}
	public static <T> void export(String path, IConverter<T> converter, List<T> list)
	{
		Writer writer = null;
		try 
		{
			writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8);
			
			writeLine(writer, converter.convert(null));
			for(T item : list)
				writeLine(writer, converter.convert(item));
			
			writer.flush();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(writer != null)
					writer.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
