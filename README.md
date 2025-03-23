# march23_2025
The problem that i solved today in leetcode

1.You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

Code:
class Solution {
    ArrayList<int[]>[] adj;
    public int countPaths(int n, int[][] roads) {
        adj=new ArrayList[n];
        int i;
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:roads)
        {
            adj[x[0]].add(new int[]{x[1],x[2]});
            adj[x[1]].add(new int[]{x[0],x[2]});
        }
        long[] dist=new long[n];
        long[] ways=new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[0]=0;
        ways[0]=1;
        PriorityQueue<long[]> pq=new PriorityQueue<>((a,b)->Long.compare(a[1],b[1]));
        pq.add(new long[]{0,0});
        while(!pq.isEmpty())
        {
            long[] x=pq.poll();
            int u=(int)x[0];
            long dis=x[1];
            for(int[] a:adj[u])
            {
                int v=a[0];
                int d=a[1];
                if(dis+d<dist[v])
                {
                    dist[v]=dis+d;
                    pq.add(new long[]{v,dist[v]});
                    ways[v]=ways[u]%1000000007;
                }
                else if(dis+d==dist[v])
                    ways[v]=(ways[u]+ways[v])%1000000007;
            }
        }
        return (int)ways[n-1];
    }
}
