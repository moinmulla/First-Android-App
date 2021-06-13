import java.util.Scanner;
abstract class Cipher{
	String plaintext;
	int key;
	Cipher(String s,int k){
		plaintext=s;
		key=k;
	}
	abstract String Encryption();
	abstract String Decryption();
}
class Substitution_Cipher extends Cipher{
	char sub[]={'q','a','z', 'w', 's', 'x', 'e', 'd', 'c', 'r', 'f', 'v', 't', 'g', 'b', 'y', 'h', 'n', 'u', 'j', 'm', 'i', 'k','o', 'l', 'p'};
	Substitution_Cipher(String s1,int k1){
		super(s1,k1);
	}
	String Encryption(){
		plaintext=plaintext.toLowerCase();
		char a[]=plaintext.toCharArray();

		for(int i=0;i<a.length;i++){
			a[i]=sub[a[i]-97];
		}

		String s=new String(a);
		return s;
	}

	String Decryption(){
		plaintext=plaintext.toLowerCase();
		char a[]=plaintext.toCharArray();
		int j;
		for(int i=0;i<a.length;i++){
			for(j=0;j<26;j++)
			{
				if(a[i]==sub[j])
					break;
			}
			int k=j+97;
			a[i]=(char)k;
		}

		String s=new String(a);
		return s;
	}	
}

class Ceaser_Cipher extends Cipher{
	Ceaser_Cipher(String s2,int k2){
		super(s2,k2);
	}
	String Encryption(){
		plaintext=plaintext.toLowerCase();
		char a[]=plaintext.toCharArray();

		for(int i=0;i<a.length;i++){
			a[i]=(char)(a[i]+key);
		}

		plaintext=String.valueOf(a);
		return plaintext;
	}

	String Decryption(){
		plaintext=plaintext.toLowerCase();
		char a[]=plaintext.toCharArray();
		int j;
		for(int i=0;i<a.length;i++){
			a[i]=(char)(a[i]-key);
		}

		plaintext=String.valueOf(a);
		return plaintext;
	}
}
class set{
	public static void main(String a[]){
		System.out.print("Enter text:");
		Scanner sc=new Scanner(System.in);
		String t=sc.next();
		char abc[]=t.toCharArray();
		Cipher s=new Substitution_Cipher(t,abc.length);
		t=s.Encryption();
		System.out.println("Encryted text is- "+t);

		System.out.print("Enter text:");
		String p=sc.next();
		char def[]=p.toCharArray();
		Cipher q=new Substitution_Cipher(p,def.length);
		p=q.Decryption();
		System.out.println("Decryted text is- "+p);

		System.out.print("Enter text:");
		String e=sc.next();
		Cipher st=new Ceaser_Cipher(e,1);
		System.out.println("Encrypted -"+st.Encryption());
		System.out.println("Decrypted -"+st.Decryption());
	}
}