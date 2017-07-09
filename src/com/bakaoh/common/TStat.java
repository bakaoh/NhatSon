package com.bakaoh.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author taitt
 */
public class TStat
{

    private Map<String, StatItem> stats;
    private int currentDay;

    public TStat()
    {
        stats = new HashMap<>();
    }

    public void exec(String method, long execTime)
    {
        if (!stats.containsKey(method))
        {
            newMethod(method);
        }
        StatItem stat = stats.get(method);
        stat._totalHits.incrementAndGet();
        int _cd = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        if (_cd != currentDay)
        {
            currentDay = _cd;
            stat.clearSecondsData();
        }
        int second = getCurrentSecond();
        stat.__hitInSecond.inc(second);
        stat.__timeExecutedInSecond.set(second, execTime);
    }

    public String printStat()
    {
        int second = getCurrentSecond();
        StringBuilder output = new StringBuilder();
        output.append("----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        output.append("Method\t\t  Total Request\t Last RPS/ Last Exe Time\t\tMax RPS      Min RPS      Avg RPS      Max ExeT      Min ExeT      Avg ExeT\n");
        output.append("----------------------------------------------------------------------------------------------------------------------------------------------------\n");

        for (String method : stats.keySet())
        {
            StatItem stat = stats.get(method);
            output.append(padRight(method, 19));
            output.append(padRight(String.format("%,d", stat._totalHits.get()), 14));
            if (second > 5)
            {
                String lastRPS = String.format("%,d %,d %,d %,d %,d",
                    stat.__hitInSecond.get(second - 1),
                    stat.__hitInSecond.get(second - 2),
                    stat.__hitInSecond.get(second - 3),
                    stat.__hitInSecond.get(second - 4),
                    stat.__hitInSecond.get(second - 5));
                output.append(padRight(lastRPS.toString(), 39));
            }
            if (second > 60)
            {
                output.append(padRight(String.format("%s", maxRps(method, second - 60, second)), 13));
                output.append(padRight(String.format("%s", minRps(method, second - 60, second)), 13));
                output.append(padRight(String.format("%.3f", avgRps(method, second - 60, second)), 13));
                output.append(padRight(String.format("%s", maxExeT(method, second - 60, second)), 14));
                output.append(padRight(String.format("%s", minExeT(method, second - 60, second)), 14));
                output.append(padRight(String.format("%.3f", avgExeT(method, second - 60, second)), 10));
            }
            output.append("\n");
            if (second > 5)
            {
                String lastExeT = String.format("%,d %,d %,d %,d %,d",
                    stat.__timeExecutedInSecond.get(second - 1),
                    stat.__timeExecutedInSecond.get(second - 2),
                    stat.__timeExecutedInSecond.get(second - 3),
                    stat.__timeExecutedInSecond.get(second - 4),
                    stat.__timeExecutedInSecond.get(second - 5));
                output.append("\t\t\t\t ").append(lastExeT.toString());
            }
            output.append("\n");
        }
        return output.toString();
    }

    public String htmlStat()
    {
        int second = getCurrentSecond();
        StringBuilder output = new StringBuilder("<table border=\"1\" cellpadding=\"3\" cellspacing=\"0\">");
        output.append("<tr><th>Method</th><th>Total</th><th>Last RPS</th><th>Last ExeT</th><th>Max RPS</th><th>Min RPS</th><th>Avg RPS</th><th>Max ExeT</th><th>Min ExeT</th><th>Avg ExeT</th></tr>");

        for (String method : stats.keySet())
        {
            StatItem stat = stats.get(method);
            output.append("<tr>").append(td(method));
            output.append(td(String.format("%,d", stat._totalHits.get())));
            if (second > 5)
            {
                String lastRPS = String.format("%,d %,d %,d %,d %,d",
                    stat.__hitInSecond.get(second - 1),
                    stat.__hitInSecond.get(second - 2),
                    stat.__hitInSecond.get(second - 3),
                    stat.__hitInSecond.get(second - 4),
                    stat.__hitInSecond.get(second - 5));
                output.append(td(lastRPS));
                String lastExeT = String.format("%,d %,d %,d %,d %,d",
                    stat.__timeExecutedInSecond.get(second - 1),
                    stat.__timeExecutedInSecond.get(second - 2),
                    stat.__timeExecutedInSecond.get(second - 3),
                    stat.__timeExecutedInSecond.get(second - 4),
                    stat.__timeExecutedInSecond.get(second - 5));
                output.append(td(lastExeT));
            }
            if (second > 60)
            {
                output.append(td(String.format("%s", maxRps(method, second - 60, second))));
                output.append(td(String.format("%s", minRps(method, second - 60, second))));
                output.append(td(String.format("%.3f", avgRps(method, second - 60, second))));
                output.append(td(String.format("%s", maxExeT(method, second - 60, second))));
                output.append(td(String.format("%s", minExeT(method, second - 60, second))));
                output.append(td(String.format("%.3f", avgExeT(method, second - 60, second))));
            }
            output.append("</tr>");
        }
        output.append("</table>");
        return output.toString();
    }

    private static String padRight(String s, int n)
    {
        return String.format("%1$-" + n + "s", s);
    }

    private String td(String td)
    {
        return "<td>" + td + "</td>";
    }

    private static int getCurrentSecond()
    {
        Calendar midNight = new GregorianCalendar();
        midNight.set(Calendar.HOUR_OF_DAY, 0);
        midNight.set(Calendar.MINUTE, 0);
        midNight.set(Calendar.SECOND, 0);
        midNight.set(Calendar.MILLISECOND, 0);
        int res = (int) ((System.currentTimeMillis() - midNight.getTimeInMillis()) / 1000);
        return res;
    }

    private synchronized void newMethod(String method)
    {
        if (!stats.containsKey(method))
        {
            stats.put(method, new StatItem());
        }
    }

    private int maxRps(String method, int from, int to)
    {
        return (int) stats.get(method).__hitInSecond.max(from, to);
    }

    private int minRps(String method, int from, int to)
    {
        return (int) stats.get(method).__hitInSecond.min(from, to);
    }

    private float avgRps(String method, int from, int to)
    {
        return stats.get(method).__hitInSecond.avg(from, to);
    }

    private int maxExeT(String method, int from, int to)
    {
        return (int) stats.get(method).__timeExecutedInSecond.max(from, to);
    }

    private int minExeT(String method, int from, int to)
    {
        return (int) stats.get(method).__timeExecutedInSecond.min(from, to);
    }

    private float avgExeT(String method, int from, int to)
    {
        return stats.get(method).__timeExecutedInSecond.avg(from, to);
    }

    static class StatItem
    {

        public AtomicLong _totalHits = new AtomicLong();
        public TDayStat __hitInSecond = new TDayStat();
        public TDayStat __timeExecutedInSecond = new TDayStat();

        public void clearSecondsData()
        {
            __hitInSecond.clear();
            __timeExecutedInSecond.clear();
        }

        public void clearTotalHits()
        {
            _totalHits.set(0);
        }
    };

    static class TDayStat
    {

        private int TSECOND = 60 * 60 * 24;
        private AtomicLong[] _data;

        public TDayStat()
        {
            _data = new AtomicLong[TSECOND];
            for (int i = 0; i < TSECOND; i++)
            {
                _data[i] = new AtomicLong();
            }
        }

        boolean validPos(int pos)
        {
            return (pos >= 0 && pos < TSECOND);
        }

        boolean validFromTo(int from, int to)
        {
            return (from >= 0 && from < TSECOND && to >= 0 && to < TSECOND && from <= to);
        }

        long get(int pos)
        {
            return validPos(pos) ? _data[pos].get() : -1;
        }

        long set(int pos, long value)
        {
            if (!validPos(pos))
            {
                return -1;
            }
            _data[pos].set(value);
            return value;
        }

        long inc(int pos)
        {
            return validPos(pos) ? _data[pos].incrementAndGet() : -1;
        }

        long add(int pos, long value)
        {
            return validPos(pos) ? _data[pos].addAndGet(value) : -1;
        }

        void clear()
        {
            for (int i = 0; i < TSECOND; i++)
            {
                _data[i].set(0);
            }
        }

        long max(int from, int to)
        {
            if (!validFromTo(from, to))
            {
                return -2;
            }
            long current_mv = 0;
            for (int i = from; i <= to; i++)
            {
                if (_data[i].get() > current_mv)
                {
                    current_mv = _data[i].get();
                }
            }
            return current_mv;
        }

        long min(int from, int to)
        {
            if (!validFromTo(from, to))
            {
                return -2;
            }
            long current_mv = Long.MAX_VALUE;
            for (int i = from; i <= to; i++)
            {
                if (_data[i].get() < current_mv)
                {
                    current_mv = _data[i].get();
                }
            }
            return current_mv;
        }

        float avg(int from, int to)
        {
            if (!validFromTo(from, to) || from == to)
            {
                return -2;
            }
            long total = 0;
            long count = 0;
            for (int i = from; i <= to; i++)
            {
                if (_data[i].get() > 0)
                {
                    total += _data[i].get();
                    count++;
                }
            }
            return (count > 0) ? ((float) total) / (to - from) : 0;
        }
    }
}
