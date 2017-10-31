import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Anagram  {

	public static List<String> array[];
	public static int prim = 40009;
	public static List<structure> v = new ArrayList();
	static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));


	private static class structure{
		private String s1;
		private List<String> l;
		public structure(String str){
			this.s1 = str;
			this.l = new ArrayList();
		}
	}

//super.Stack(20);
	private static void createTable(int num, Scanner input){
		array = new ArrayList[prim];
		for(int i=0;i<prim;i++)
		{
			array[i] = new ArrayList();
		}
		String val = input.nextLine();
		for(int i=0;i<num;i++)
		{
			 val = input.nextLine();
		//	System.out.println(val);
			int pos = hash(val);
			
			array[pos].add(val);
		}
		return ;
	}
	private static int hash(String val){
		char val1[] = val.toCharArray();

		Arrays.sort(val1);
		int hash = 7;
		for(int i=0;i<val.length();i++)
		{	
			hash = ((hash*31)%prim + (int)val1[i])%prim;
		}
		return hash;
	}
	private static void Print(){
		for(int i=0;i<prim;i++)
		{
			System.out.println(array[i]);
		}
	}
	private static void searchTable(Scanner input,int value) throws Exception{
		if(value == 2)
			return ;
		int num = input.nextInt();
		String s1 = input.nextLine();
		//System.out.println(num+" "+num1);
		OutputWriter out = new OutputWriter(System.out);
		for(int i=0;i<num;i++)
		{
			String val = input.nextLine();
			//List<String> ne = new ArrayList();
			// if(val.length() < 5)
			Set<String> ne = new HashSet<String>();
			if(val.length()<=12)
			{
				search(val,0,ne,out);
				if(val.length() > 5)
				search2(val,ne);


				Set<String> tree_Set = new TreeSet<String>(ne);
			    Iterator<String> itr = tree_Set.iterator();
		        //System.out.println(tree_Set);	
				 while(itr.hasNext()){
				 	String str = itr.next();
					// System.out.println(str);
					log.write(str+"\n");
				 } 
				 log.write("-1\n");
			}
			// System.out.println("-1");

		}

		// out.close();
	}
	static Set getCombinations(String instr, StringBuffer outstr, int index,int size)
	{
		Set combinations = new HashSet();
		// combinations.add(instr);
		for (int i = index; i < instr.length(); i++)
		{
			outstr.append(instr.charAt(i));
			// if(outstr.toString().length() >2 && outstr.toString().length() < instr.length() -2)
			if(outstr.toString().length() == size)
			combinations.add(outstr.toString());
			combinations.addAll(getCombinations(instr, outstr, i + 1,size));
			outstr.deleteCharAt(outstr.length() - 1);
		}
		return combinations;
	}

	private static void search2(String val , Set ne)
	{
		int n = val.length();
		for(int i0=3;i0<=val.length()/2;i0++){
			StringBuffer sb = new StringBuffer();
			Set set = getCombinations(val,sb,0,i0);
			Set tree = new TreeSet(set);
			Iterator<String> itr = tree.iterator();
			{
				while(itr.hasNext())
				{
					String str = itr.next();

					int pos = hash(str);
					char ar[],ar1[];
					String a="",b="";
					for(int i=0;i<array[pos].size();i++)
					{
						if(i==0)
						{
							
							ar = str.toCharArray();
							Arrays.sort(ar);
							
							a =  new String(ar);
						}
						ar1 = array[pos].get(i).toCharArray();
						Arrays.sort(ar1);
						b = new String(ar1);
						if(a.equals(b))
						{
							int m = a.length();
							StringBuffer n10 =new StringBuffer();
							int alp =0;
							for(int p=0;p<n&&alp <m;p++)
							{
								if(str.charAt(alp) == val.charAt(p))
								{	
								//	p++;
									alp++;
								}
								else
								{
								//	System.out.println(n1+" "+p);
									n10.append(val.charAt(p));
								}
								if(alp == m)
								{
									for(int al=p+1;al<n;al++)
									{
										n10.append(val.charAt(al));
									}
								}
							}
							String n1 = n10.toString();
							int pos2 = hash(n1);
							char ar2[],ar3[];
							String c="",d="";
							for(int i1=0;i1<array[pos2].size();i1++)
							{
								if(i1==0)
								{
									ar2 = n1.toCharArray();
									Arrays.sort(ar2);
									c = new String(ar2);
								}
								ar3 = array[pos2].get(i1).toCharArray();
								Arrays.sort(ar3);
								d = new String(ar3);
								if(c.equals(d))
								{
									String e,f;
									e = array[pos].get(i);
									f= array[pos2].get(i1);
									ne.add(f+" "+e);
									if(!e.equals(f))
										ne.add(e+" "+f);
									
								}
							}
							//================================================================================================
							//============================ now to compute 2 space words ======================================
							//================================================================================================
							if(i0 <= val.length()/3)
							{
								int si  = n1.length();
								for(int i00 =3;i00<=n1.length()/2;i00++)
								{	
									StringBuffer sb1 = new StringBuffer();
									Set s1 = getCombinations(n1,sb1,0,i00);
									Set tr = new TreeSet(s1);
									Iterator<String> it = tr.iterator();
									{
										while(it.hasNext())
										{
											String st = it.next();

											int pos1 = hash(st);
											char br[],br1[];
											String a1 ="",b1 ="";
											for(int q=0; q<array[pos1].size();q++)
											{
												if(q==0)
												{
													br = st.toCharArray();
													Arrays.sort(br);
													a1 = new String(br);
												}
												br1 = array[pos1].get(q).toCharArray();
												Arrays.sort(br1);
												b1 = new String(br1);
												if(a1.equals(b1))
												{
													int m1 = a1.length();
													String n11 = "";
													int lp = 0;
													for (int h=0;h<si&&lp<m1 ;h++ ) {
														if(st.charAt(lp) == n1.charAt(h))
															lp++;
														else
															n11 += n1.charAt(h);
														if(lp == m1)
														{
															for(int w=h+1;w<si;w++)
															{
																n11+=n1.charAt(w);
															}
														}
														
													}
													int pos3 = hash(n11);
													char br2[],br3[];
													String c1="",d1="";
													for(int w1=0;w1<array[pos3].size();w1++)
													{
														if(w1==0)
														{
															br2 = n11.toCharArray();
															Arrays.sort(br2);
															c1 = new String(br2);
														}
														br3 = array[pos3].get(w1).toCharArray();
														Arrays.sort(br3);
														d1 = new String(br3);
														if(c1.equals(d1))
														{
															String z1,z2,z3;
															z1 = array[pos].get(i);
															z2 = array[pos1].get(q);
															z3 = array[pos3].get(w1);
															ne.add(z1+" "+z2+" "+z3);
															if(z1.equals(z2)&&z1.equals(z3))
																continue;
															else
															{
																// if(z1.equals(z2))
																// {
																// 	ne.add(z1+" "+z3+" "+z2);
																// 	ne.add(z3+" "+z1+" "+z2);
																// }
																// else if(z1.equals(z3))
																// {
																// 	ne.add(z2+" "+z1+" "+z3);	
																// 	ne.add(z1+" "+z3+" "+z2);
																// }
																// else if(z2.equals(z3))
																// {
																// 	ne.add(z3+" "+z1+" "+z2);
																// 	ne.add(z3+" "+z2+" "+z1);	
																// }
																// else
																{
																	ne.add(z3+" "+z1+" "+z2);
																	ne.add(z3+" "+z2+" "+z1);
																	ne.add(z2+" "+z1+" "+z3);
																	ne.add(z2+" "+z3+" "+z1);
																	ne.add(z1+" "+z3+" "+z2);
																}
																
																// ne.add(z2+" "+z3+" "+z1);
																
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

					}
				}
			}
		}
	}



	private static void search(String val, int value,Set<String> ne,OutputWriter out)
	{
		if(value == 2)
		{
			return ;
		}
		int pos = hash(val);
		char var[],var1[];
		var = val.toCharArray();
		Arrays.sort(var);
		String a,b;
		a = new String(var);

		for(int j=0;j<array[pos].size();j++)
		{
			//System.out.println(array[pos]);
			var1 = array[pos].get(j).toCharArray();
			
			Arrays.sort(var1);
			
			b = new String(var1);
			//System.out.println(a + "  hi guys   "+b);
			if(a.equals(b))
			{
				ne.add(array[pos].get(j));
			}
		}
		// StringBuffer sb = new StringBuffer();
		// combitor(val)
		// getCombinations(val,sb,0);
		//System.out.println(ne);
		// if(val.length() >5)
		// combitor(val,ne);
		// System.out.println(a);
		// iterating over the table to get a pair of triple angram
		// for(int k=0;k<v.size();k++)
		// {
		// 	int n = val.length();
		// 	int m = v.get(k).s1.length();
		// 	String s = v.get(k).s1;

		// 	if(issub(v.get(k).s1,a,m,n))
		// 	{
		// 		String n1 = "";
		// 		int alp = 0;
		// 	//	System.out.print(s+" "+a);
		// 		for(int p=0;p<n&&alp <m;p++)
		// 		{
		// 			if(s.charAt(alp) == a.charAt(p))
		// 			{	
		// 			//	p++;
		// 				alp++;
		// 			}
		// 			else
		// 			{
		// 			//	System.out.println(n1+" "+p);
		// 				n1 += a.charAt(p);
		// 			}
		// 			if(alp == m)
		// 			{
		// 				for(int al=p+1;al<n;al++)
		// 				{
		// 					n1+=a.charAt(al);
		// 				}
		// 			}
		// 		}
		// 	//	System.out.println(" "+n1);
		// 		if(n1.length()>2)
		// 		{
		// 			//System.out.println(n1+" "+s);
		// 			for(int rep =0;rep<v.get(k).l.size();rep++)
		// 			{
		// 				search1(n1,value+1,ne,v.get(k).l.get(rep));	
		// 			}
		// 		}
		// 	}
		// }


	}


	private static void combitor(String val,Set<String> se){
		StringBuffer sb = new StringBuffer();
		Set set = getCombinations(val,sb,0,0);
		Set<String> tree = new TreeSet<String>(set);
		Iterator<String> itr = tree.iterator();
		int n = val.length();
		while(itr.hasNext()){
			String str = itr.next();
			int pos = hash(str);
			char var[],var1[];
			var = str.toCharArray();
			Arrays.sort(var);
			String a,b;
			a = new String(var);
			int m = str.length();
			String n1 ="";
			int alp =0;
			for(int p=0;p<n&&alp <m;p++)
			{
				if(str.charAt(alp) == val.charAt(p))
				{	
				//	p++;
					alp++;
				}
				else
				{
				//	System.out.println(n1+" "+p);
					n1 += val.charAt(p);
				}
				if(alp == m)
				{
					for(int al=p+1;al<n;al++)
					{
						n1+=val.charAt(al);
					}
				}
			}
			for(int j=0;j<array[pos].size();j++)
			{
				//System.out.println(array[pos]);
				var1 = array[pos].get(j).toCharArray();
				
				Arrays.sort(var1);
				
				b = new String(var1);
				//System.out.println(a + "  hi guys   "+b);
				if(a.equals(b))
				{

					int pos1 = hash(n1);
					char var2[],var3[];
					var2 = n1.toCharArray();
					Arrays.sort(var2);
					String c,d;
					c = new String(var2);
					for(int ji=0;ji<array[pos1].size();ji++)
					{
						//System.out.println(array[pos]);
						var3 = array[pos1].get(ji).toCharArray();
						
						Arrays.sort(var3);
						
						d = new String(var3);
						//System.out.println(a + "  hi guys   "+b);
						if(c.equals(d))
						{
							se.add(array[pos].get(j)+" "+array[pos1].get(ji));
						}

					}
					//System.out.println(str+" "+n1+"0000");
					StringBuffer sb1 = new StringBuffer();
					Set<String> set1 = getCombinations(n1,sb,0,0);
					// copy pasting and adjusting
					Set<String> tree1 = new TreeSet<String>(set1);
					Iterator<String> itr1 = tree1.iterator();
					int n0 = n1.length();
					while(itr1.hasNext()){
						String str1 = itr1.next();
						int pos3 = hash(str1);
						char var4[],var5[];
						var4 = str1.toCharArray();
						Arrays.sort(var4);
						String e,f;
						e = new String(var4);
						int m0 = str1.length();
						String n11 ="";
						int alp1 =0;
						for(int p1=0;p1<n0&&alp1 <m0;p1++)
						{
							if(str1.charAt(alp1) == n1.charAt(p1))
							{	
							//	p++;
								alp1++;
							}
							else
							{
							//	System.out.println(n1+" "+p);
								n11 += n1.charAt(p1);
							}
							if(alp1 == m0)
							{
								for(int al1=p1+1;al1<n0;al1++)
								{
									n11+=n1.charAt(al1);
								}
							}
						}
						//System.out.println(str1+" "+n11+"000");
						for(int j1=0;j1<array[pos3].size();j1++)
						{
							//System.out.println(array[pos]);
							var5 = array[pos3].get(j1).toCharArray();
							
							Arrays.sort(var5);
							
							f = new String(var5);
							//System.out.println(a + "  hi guys   "+b);
							if(e.equals(f))
							{

								int pos4 = hash(n11);
								char var6[],var7[];
								var6 = n11.toCharArray();
								Arrays.sort(var6);
								String g,h;
								g = new String(var6);
								for(int ji1=0;ji1<array[pos4].size();ji1++)
								{
									//System.out.println(array[pos]);
									var7 = array[pos4].get(ji1).toCharArray();
									
									Arrays.sort(var7);
									
									h = new String(var7);
									//System.out.println(a + "  hi guys   "+b);
									if(g.equals(h))
									{
										se.add(array[pos].get(j)+" "+array[pos3].get(j1)+" "+array[pos4].get(ji1));
									}

								}
								// StringBuffer sb1 = new StringBuffer();
								// Set<String> tree1 = getCombinations(n1,sb,0)
								
							}
						}
					}


				}
			}
		}

	}


	private static void search1(String val, int value,Set<String> ne,String pal)
	{
		
		int pos = hash(val);
		char var[],var1[];
		var = val.toCharArray();
		Arrays.sort(var);
		String a,b;
		a = new String(var);
		
		for(int j=0;j<array[pos].size();j++)
		{
			var1 = array[pos].get(j).toCharArray();
			Arrays.sort(var1);
			b = new String(var1);
			//System.out.println(a + "  hi guys   "+b);
			if(a.equals(b))
			{
				ne.add(pal+" "+array[pos].get(j));
			}
		}


		if(value == 2)
		{
			return ;
		}
		if(val.length() <6)
			return;
	// iterating over the table to get a pair of triple angram
	//	System.out.println(pal);
		for(int k=0;k<v.size();k++)
		{
			int n = val.length();
			int m = v.get(k).s1.length();
			String s = v.get(k).s1;
			if(issub(s,a,m,n))
			{
				String n1 = "";
				int alp = 0;
		//		System.out.print(s+" "+a);
				for(int p=0;p<n&&alp <m;p++)
				{
					if(s.charAt(alp) == a.charAt(p))
					{	
					//	p++;
						alp++;
					}
					else
					{
					//	System.out.println(n1+" "+p);
						n1 += a.charAt(p);
					}
					if(alp == m)
					{
						for(int al=p+1;al<n;al++)
						{
							n1+=a.charAt(al);
						}
					}
				}
		//		System.out.println(" "+n1);
				if(n1.length()>2)
				{
					//System.out.println(n1+" "+s);
					for(int rep =0;rep<v.get(k).l.size();rep++)
					{
						search1(n1,value+1,ne,pal+" "+v.get(k).l.get(rep));	
					}
				}
				n1 = "";
			}
		}
		//System.out.println(ne); 
		//System.out.println("-1");
	}


	// public static String n1 = "";
	private static boolean issub(String a , String b,int m,int n){
		 int j = 0;

        for (int i=0; i<n&&j<m; i++)
        {
            if (a.charAt(j) == b.charAt(i))
                j++;
            // else
            // 	n1 +=b.charAt(i);
            // if(j == m)
            // {
            // 	for(int k=i+1;k<n;k++)
            // 		n1+=b.charAt(k);
            // }
        }
        
 
        // If all characters of str1 were found in str2
        if(j==m)
        	return true;
        else
        	return false; 
	} 

	private static void  makevector(){
		
		for(int i=0;i<prim;i++)
		{
			array[i].sort(new Comparator<String>(){
				@Override
				public int compare(String s1,String s2){
					char a[],b[];
					a = s1.toCharArray();
					b = s2.toCharArray();
					Arrays.sort(a);
					Arrays.sort(b);
					String c,d;
					c = new String(a);
					d = new String(b);
					if(c.compareTo(d) > 0)
						return 1;
					else if(c.compareTo(d) == 0)
						return 0;
					else
						return -1;
				}
			});
			String old = "";
			int pr =0;
			structure s = null;
			if(array[i].size() > 0)
			//System.out.println("here at makevector" + array[i].size());
			for(int j=0;j<array[i].size();j++)
			{
				char a[] = array[i].get(j).toCharArray();
				Arrays.sort(a);
				String b = new String(a);
				if(j == 0)
				{
					pr = 1;
					s = new structure(b);
					old = b;
					s.l.add(array[i].get(j));
				}
				else if( b.equals(old))
				{
					s.l.add(array[i].get(j));
				}
				else{
					v.add(s);
					s = new structure(b);
					old = b;
					s.l.add(array[i].get(j));
				}
			//	System.out.println(s + "hu"+b);
			}
			if(pr==1)
			{
				v.add(s);
			}
		}
	}

	private static void Print1(){
		System.out.println("hi there" +v.size());
		for(int i=0;i < v.size();i++)
		{
			if(v.get(i).s1.equals("2dn"))
				System.out.println(i);
			//System.out.println("                 "+v.get(i).s1);
			//System.out.println();
			for(int j=0;j<v.get(i).l.size();j++)
			{
			//	System.out.println(v.get(i).l.get(j));
			}
		}
	}

	private static class OutputWriter {
		private final PrintWriter writer;
		
		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}
		
		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}
		
		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}
		
		public void printLine(Object... objects) {
			print(objects);
			writer.println();
			writer.flush();
		}
		
		public void close() {
			writer.close();
		}
		
		public void flush() {
			writer.flush();
		}
	}


	public static void main(String[] args) throws Exception{
		
		try{
			long startTime=System.currentTimeMillis();
		/*Scanner input = new Scanner(new File(args[0]));
		
		int num = input.nextInt();*/
			Scanner vocab = new Scanner(new File(args[0]));
			Scanner input = new Scanner(new File(args[1]));

			// start reading vocab
			int num = vocab.nextInt();
			createTable(num , vocab);
		// 	StringBuffer sb =new StringBuffer();
		// OutputWriter out = new OutputWriter(System.out);
		// 	for(long i=0;i<9500000;i++)
		// 		sb.append("-1").append('\n');
		// 	String s = sb.toString();
		// 		System.out.println(s);


        // for (int i = 0; i < 9500000; i++) {
        //     log.write("-1\n");
        // }
		      // OutputStream out = new BufferedOutputStream ( System.out );
        // for (int i = 0; i < 9500000; i++) {
        //     out.write(( "-1\n").getBytes());
        // }
        // out.flush();

				// out.printLine(s);
			// System.out.println(getCombinations("senilkumar",sb,0,3));
			// makevector();
			searchTable(input,0);
	        log.flush();

		//	System.out.println(v.size());
		//S	for(int i=0;i<20;i+=2)
		//	System.out.println(v.get((35802+i)/2).s1);
		//	Print1();
	  	// Print();
			long time=System.currentTimeMillis()-startTime;
			System.out.println("time: "+time+" millis");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
		}
	}
}
