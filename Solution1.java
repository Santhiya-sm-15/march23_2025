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