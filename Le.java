package po.leit.ui;

import java.io.*;

/**
 * Classe simples para ler dados de entrada .
 * @versão 2
 * @autor: Paulo Marques (pmarques@dei.uc.pt)
 */

public class Le
{
    //private static final int BUF_SIZE = 1024;

    // Inibe o constructor por defeito
    private Le()
    {
    }

    /**
     * Le um inteiro da entrada padrão.
     * A entrada é terminada com um return. Se a entrada não for válida é mostrada a mensagem
     * "!!! Não é um inteiro !!!" e o utilizador pode tentar de novo.
     *
     * @devolve o número lido
     */
    public static int umInt()
    {
        while(true)
        {
            try
            {
                return Integer.valueOf(umaString().trim()).intValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Não é um inteiro !!!");
            }
        }
    }

    /**
     * Le um double da entrada padrão.
     * A entrada é terminada com um return. Se a entrada não for válida é mostrada a mensagem
     * "!!! Não é um double !!!" e o utilizador pode tentar de novo.
     *
     * @devolve o número lido
     */
    public static double umDouble()
    {
        while(true)
        {
            try
            {
                return Double.valueOf(umaString().trim()).doubleValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Não é um double !!!");
            }
        }
    }

    /**
     * Le um float da entrada padrão.
     * A entrada é terminada com um return. Se a entrada não for válida é mostrada a mensagem
     * "!!! Não é um float !!!" e o utilizador pode tentar de novo.
     *
     * @devolve o número lido
     */
    public static float umFloat()
    {
        while(true)
        {
            try
            {
                return Float.valueOf(umaString().trim()).floatValue();
            }
            catch(Exception e)
            {
                System.out.println("!!! Não é um float !!!");
            }
        }
    }


    /**
     * Le uma String da entrada padrão.
     * A entrada é terminada com um return.
     *
     * @devolve a String lida
     */
    public static String umaString()
    {
        String s = "";

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            s = in.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error reading from the input stream.");
        }

        return s;
    }

    /**
     * Le um caracter da entrada padrão.
     * A entrada é terminada com um return.
     *
     * @devolve o caracter lido
     */
    public static char umChar()
    {
        char c=' ';

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            c = (char)in.read();
        }
        catch (IOException e)
        {
            System.out.println("Error reading from the input stream.");
        }

        return c;
    }


}
