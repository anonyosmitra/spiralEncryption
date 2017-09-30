import java.util.*;
public class spiralEncryption
{
	static String list="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890?.,!@#$%^&*()`~-_=+[{]};:'\"<>/ |\n";
	static int listSize=list.length();
	static char s1[]=new char[listSize];
	static char s2[]=new char[listSize];
	static String user="",pass="",code="",msg="";
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Username: ");
		user=sc.nextLine();
		System.out.print("Password: ");
		pass=sc.nextLine();
		System.out.println("1-Coding");
		System.out.println("2-Decoding");
		int in=sc.nextInt();
		if(in==1)
			coding();
		if(in==2)
			decoding();
		if(in==3)
			test();
		System.out.println("-----------------------------------------------------An Anonyo Product © 2017-----------------------------------------------------");
	}
	public static void coding()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Msg:");
		msg=sc.nextLine();
		coder();
		System.out.println("Code:"+code);
	}
	public static void decoding()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("code:");
		code=sc.nextLine();
		decoder();
		System.out.println("Msg:"+msg);
	}
	public static void test()
	{
		System.out.println("Sample msg:"+list);
		msg=list;
		coder();
		System.out.println("Code: "+code);
		System.out.println("Decoding");
		decoder();
		System.out.println("Decoded msg: "+msg);
		if(msg==list)
			System.out.println("Matched:Test Successful!");
		else
			System.out.println("Error:Test failed!");
		System.out.println(msg);
		System.out.println(list);
	}
	/*																														User Interface
----------------------------------------------------------------------------------------------------------------------------------------------
																																Coding/Decoding
*/
	public static void coder()
	{
		code="";
		seqHandler();
		for(int i=0;i<msg.length();i++)
		{
			for(int j=0;j<listSize;j++)
				if(s1[j]==msg.charAt(i))
					code=code+s2[j];
		}
	}
	public static void decoder()
	{
		msg="";
		seqHandler();
		for(int i=0;i<code.length();i++)
		{
			for(int j=0;j<listSize;j++)
				if(s2[j]==code.charAt(i))
					msg=msg+s1[j];
		}
	}
	/*																													Coding/Decoding
-----------------------------------------------------------------------------------------------------------------------------------------------
																															Pattern Generating
	*/
	public static void seqHandler()
	{
		/*System.out.println("List:");
		System.out.println(list);//list information
		System.out.println("List Size: "+listSize);*/
		String userName=user;
		String password=pass;
		int tlen=userName.length()+password.length();
		userName=filterinp(userName);
		password=filterinp(password);
		s1=genSeq(userName);
		s2=genSeq(password);
		s2=scramble(s2,tlen);
		//---------------------------------------------------------Print Pattern--------------------------------------------------------------------
		/*for(int i=0;i<listSize;i++)
			System.out.println(s1[i]+" - "+s2[i]);
		debug();*/
	}
	//----------------------------------------------------------------Bug Tester-------------------------------------------------------------------
	/*
	public static void debug()
	{
		System.out.println("Looking for missing characters(s1)......");
		for(int i=0;i<listSize;i++)
		{
			int b=0;
			for(int j=0;j<listSize;j++)
				if(list.charAt(i)==s1[j])
					b++;
			if(b==0)
			System.out.println(list.charAt(i));
		}
		System.out.println("Looking for missing characters(s2)......");
		for(int i=0;i<listSize;i++)
		{
			int b=0;
			for(int j=0;j<listSize;j++)
				if(list.charAt(i)==s2[j])
					b++;
			if(b==0)
			System.out.println(list.charAt(i));
		}
		System.out.println("Looking for repeated characters(s1)......");
		for(int i=0;i<listSize;i++)
		{
			int b=0;
			for(int j=0;j<listSize;j++)
				if(s1[i]==s1[j])
					b++;
			if(b>1)
				System.out.println(s1[i]+"Repeats: "+b);
		}
		System.out.println("Looking for repeated characters(s2)......");
		for(int i=0;i<listSize;i++)
		{
			int b=0;
			for(int j=0;j<listSize;j++)
				if(s2[i]==s2[j])
					b++;
			if(b>1)
				System.out.println(s2[i]+"Repeats: "+b);
		}
	}
	*/
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public static String filterinp(String inp)
	{
		String out=""+inp.charAt(0);
		int len=1;
		for(int i=1;i<inp.length();i++)
			{
				int b=1;
				for(int j=0;j<len;j++)
					if(inp.charAt(i)==out.charAt(j))
						b=0;
				if(b==1)
					{
						out=out+inp.charAt(i);
						len++;
					}			
			}
		return(out);
	}
	public static char[] genSeq(String in)
	{
		char a[]=new char[listSize];
		int i=0;
		for(;i<in.length();i++)
			a[i]=in.charAt(i);
		for(int j=0;j<listSize;j++)
		{
			int b=1;
			for(int k=0;k<in.length();k++)
				if(list.charAt(j)==in.charAt(k))
					b=0;
			if(b==1)
			{
				a[i]=list.charAt(j);
				i++;
			}
		}
		return(a);
	}
	public static char[] delCell(char[] a,int i,int size)
	{
		char b[]=new char[size-1];
		for(int j=0;j<i;j++)
			b[j]=a[j];
		for(int j=i;j<size-1;j++)
			b[j]=a[j+1];
			return(b);
	}	
	public static char[] scramble(char[] s,int tlen)
	{
		char a[]=new char[listSize];
		int u=0, size=listSize;
		for(int i=0;i<listSize;i++)
		{
			u=u+tlen;
			while(u>=size)
				u=u-size;
			a[i]=s[u];
			s=delCell(s,u,size);
			size--;
		}
		return(a);	
	}
}
//-----------------------------------------------------------An Anonyo Product © 2017--------------------------------------------------------------
