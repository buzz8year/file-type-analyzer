package analyzer.core.impl;

import analyzer.core.Analyzer;
import analyzer.Result;
import analyzer.core.Analyzer;

public class NaiveAnalyzer implements Analyzer
{
    @Override
    public <T extends Result> boolean analyze(T result)
    {
        byte[] bytes = result.getBytes();

        char[] matches = new char[result.getPattern().length()];
        boolean found = false;

        int t = 0;
        while (t < bytes.length)
        {
            for (int i = 0; i < result.getPattern().length(); i++)
            {
                int shift = t + i >= bytes.length ? bytes.length - 1 : t + i;

                if ((char)bytes[shift] == result.getPattern().charAt(i))
                    matches[i] = (char)bytes[shift];
                else break;
            }

            if (result.getPattern().equals(new String(matches)))
            {
                found = true;
                break;
            }
            else t++;
        }
        return found;
    }
}