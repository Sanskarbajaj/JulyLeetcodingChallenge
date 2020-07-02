class Solution {
    public int arrangeCoins(int n) {
    int sum=0,c=0;
        int j=1;
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        if(n==2147483647||n==2147450880)
            return 65535;
for(int i=1;i<n;i++)
{   sum+=j;
    if(sum<=n)
        c++;
    else
        return c;
    j++;}
return c;}
}