import java.io.*;
import java.util.*;

public class pw_check{
	public static DLBT good = new DLBT();//stores second list of good passwords
	public static String path="dictionary.txt";
	public static DLBT g = new DLBT(); //list of all good passwords that periodically dumps
	public static DLBT d = new DLBT();//trie for my_dictionary.txt
	public static boolean option1 = false;
	public static DLBT pre = new DLBT();
	public pw_check(){
		
	}
	public static void main(String[] args)
	{
		
		
		
		
		boolean option_one=false;
		if(args.length!=0)
		{
			option1=true;
			storeInTrie();
			generateAllPasswords();
						System.out.println("Check good_passwords.txt for a list of all good passwords.");
				//implement option one: write list of good passwords to file
		}
		else//will have to check from valid passwords; currently incorrect
		{
			
			option2();
			
		}
		}
		
	
	public static void option2()
	{
		Scanner in = new Scanner(System.in);
		String lines="";
		FileReader read=null;
		BufferedReader buff=null;
		
		String continues = "";
		while(!(continues.equals("n")||(continues.equals("N"))))
		{
			System.out.println("Please enter a possible password.");
			String password=in.nextLine();
			try{
				read = new FileReader("good_passwords.txt");
				buff = new BufferedReader(read);
			while((lines = buff.readLine())!=null){
			good.add(lines);
			if(good.getCount()==10000)
			{
				if(good.contains(password))
				{
					break;
				}
				else{
					good=new DLBT();//empty the trie to make room for memory
				}
			}
			
	}
	buff.close();
			}
			catch(Exception ex)
			{
				System.out.println();
			}
	if(good.contains(password))
	{
		System.out.println("This password is valid.");
	}
	else{
	System.out.println("The password is NOT valid.");
			recommendTen(password);
			
		}
		System.out.println("Try again? [y/n]");
		continues=in.nextLine();
		good=new DLBT();
		}
		try{
	read.close();
		}
		catch(Exception exc)
		{
			System.out.println("Could not close the file.");
		}
		
	}


	public static void storeInTrie(){
	String line="";
	try{
		FileReader read = new FileReader(path);
		BufferedReader buff = new BufferedReader(read);
		
		while((line = buff.readLine())!=null){
			if(line.length()>4)
			{
				continue;
			}
			
			d.addNumRepl(line);
			
	}
	read.close();
	}
	catch(Exception e){
		System.out.println("File not found");
	}
	try{
		BufferedWriter bw = new BufferedWriter(new FileWriter("my_dictionary.txt"));
		d.to_string("", d.getRoot(), "my_dictionary.txt", bw);
		bw.close();
	}
	catch(Exception e)
	{
		System.out.println("Writer was not created.");
	}
	}
	
	
	
	public static boolean is_valid_password(String s)
	{
		
		/*length, specifications and pruning rules section*/
		
		int chars=0;
		int syms=0;
		int nums=0;
		for(int i=0; i<s.length(); i++)
		{
			
			int value = (int)(s.charAt(i));//ASCII value of the character
			if(value==52||value==49||value==97||value==105)
			{
				return false;//password cannot contain 4, 1, a, or i
				
			}
			if(value<=122&&value>=97)
			{
				chars++;
			}
			else if(value<=57&&value>=48)
				{
				nums++;
				}
			else if(value==33||value==64||value==35||value==36||value==37||value==38||value==42)
			{
				syms++;
			}
			
		}
		if(!((chars>=1&&chars<=3)&&(nums>=1&&nums<=2)&&(syms>=1&&syms<=2)))
			{
				
			return false; //must contain 1 to 3 characters, 1 to 2 symbols, and 1 to 2 numbers
		}
		
		/*dictionary check section*/
		
		if(d.contains(s.substring(0,1)))
		{
			return false;
		}
		if(d.contains(s.substring(1,2)))
		{
			return false;
		}
		if(d.contains(s.substring(2,3)))
		{
			return false;
		}
		if(d.contains(s.substring(3,4)))
		{
			return false;
		}
		if(d.contains(s.substring(4)))
		{
			return false;
		}
		if(d.contains(s.substring(0,2)))
		{
			return false;
		}
		if(d.contains(s.substring(1,3)))
		{
			return false;
		}
		if(d.contains(s.substring(2,4)))
		{
			return false;
		}
		if(d.contains(s.substring(3)))
		{
			return false;
		}
		if(d.contains(s.substring(0,3)))
		{
			return false;
		}
		if(d.contains(s.substring(1,4)))
		{
			return false;
		}
		if(d.contains(s.substring(2)))
		{
			return false;
		}
		if(d.contains(s.substring(0,4)))
		{
			return false;
		}
		if(d.contains(s.substring(1)))
		{
			return false;
		}	
		
		
		return true;//it must be true I guess idk
	}
	
		
		
		
		
		
		
		public static void generateAllPasswords()
		{
			ArrayList<String> validPasswords = new ArrayList<String>();
			String new_pass = "";//five spaces
			char[] possible_values = {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','2','3','5','6','7','8','9','!','@','#','$','%','&','*'};//excludes a,i
			char[] possible_nums = {'2','3','5','6','7','8','9'};//excludes 4, 1
			char[] possible_syms = {'!','@','#','$','%','&','*'};
			int firstSize=1;
			int secondSize=1; 
			int thirdSize=1; 
			int fourthSize=1; 
			int fifthSize=1;
			String perm="";
			int count=0;
			int length=possible_values.length;
			
			
			BufferedWriter bw=null;
			try{
				bw = new BufferedWriter(new FileWriter("good_passwords.txt"));
			}
			catch(Exception e){
				System.out.println("File could not be opened");
			}
			
			for(int a=0; a<length; a++)
			{
				for(int b=0; b<length; b++)
				{
					for(int c=0; c<length; c++)
					{
						for(int d=0; d<length; d++)
						{
							for(int e=0; e<length; e++)
							{
								new_pass=new_pass+possible_values[a];
								new_pass=new_pass+possible_values[b];
								new_pass=new_pass+possible_values[c];
								new_pass=new_pass+possible_values[d];
								new_pass=new_pass+possible_values[e];
								if(is_valid_password(new_pass))
														{
															
															try{
															
															bw.write(new_pass);
															bw.newLine();
					
															}
															catch(Exception ex)
															{
															System.out.println("Filewriter could not be created.");
															}
															}
															
								new_pass="";
								}
								
								
							}
						}
					}
				}
				try{
				bw.close();
				}
				catch(Exception e){
					System.out.println("Could not close file.");
				}
				
			}
			
			
		public static boolean is_good_password(String s)
		{
			
			
			
			if(good.contains(s))
			{
				return true;
			}
			else{
				return false;
			}
		}
			
			
		public static void recommendTen(String s)
		{
			System.out.println("Here are ten valid passwords that are similar to yours:");
			findPrefix(s);//populates trie "pre" with passwords that share longest prefix
	
			pre.print_ten("", pre.getRoot());
			pre.resetCounter();
			pre=new DLBT();
			
		}
		//returns true if trie "pre" is populated with more than ten passwords that share the prefix
		public static boolean findPrefix(String s)
		{
			BufferedReader buff;
			String new_line = "";
			if(s.length()==0)
			{
				int count=0;
				try{
				buff=new BufferedReader(new FileReader("good_passwords.txt"));
				while((new_line = buff.readLine())!=null)
				{
					
					pre.add(new_line);
					count++;
					if(count==10)
					{
						break;
					}
				}
				buff.close();
				}
				catch(Exception e)
				{
					System.out.println("Could not open file.");
				}
				return true;
			}
			else{
			s=s.substring(0,s.length()-1);
			
			
			try{
				
			buff=new BufferedReader(new FileReader("good_passwords.txt"));
			while((new_line = buff.readLine())!=null)
			{
				if(s.equals(new_line.substring(0,s.length())))
				{
					pre.add(new_line);
				}
				if(pre.getCount()==10)
					break;
			}
			if(pre.getCount()<10)
			{
				return findPrefix(s);
			}
			buff.close();
			}
			catch(Exception e)
			{
				System.out.println("Could not open file.");
			}
			return true;
			}
		}
		
		
		
		
		
		
}