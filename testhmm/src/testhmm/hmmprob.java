package testhmm;

import java.util.ArrayList;
import java.util.List;

import be.ac.ulg.montefiore.run.jahmm.Opdf;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianMixture;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianMixtureFactory;

public class hmmprob {

	static double[] initedPi(int size)
	{
		double[] Pi=new double[size];
		
		for(int i=0;i<size;i++)
		{
			if(i==0) 	Pi[i]=1;
			else 	Pi[i]=0;
		}
		
		return Pi;
		
	}
	
	static double[][] initedA(int size)
	{
		double[][] A=new double[size][size];
		
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(i>j)
					A[i][j]=0;
				else if(j-i<=1&&j-i>=0&&i<size-1)
					A[i][j]=0.5;
				else if(i==j&&i==size-1)
					A[i][j]=1;
				else
					A[i][j]=0;
				
				System.out.print(A[i][j]+" ");
				
			}
			System.out.println();
		}
	
		return A;
	}
	
	static List<OpdfMultiGaussianMixture> initedOpdf(int size,int gaussian_num,int feature_num)
	{
		ArrayList<OpdfMultiGaussianMixture> Opdf=new ArrayList<OpdfMultiGaussianMixture>();
		
		OpdfMultiGaussianMixtureFactory fac=new OpdfMultiGaussianMixtureFactory(gaussian_num,feature_num);
		
		for(int i=0;i<size;i++)
		{
			Opdf.add(fac.factor());
		}
		
		return Opdf;
	}
	
}
