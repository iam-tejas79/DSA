class Solution {
    public int maximum69Number (int num) {
         List<Integer> nums= new ArrayList<>();
        nums.add(num);
        char[] chars= String.valueOf(num).toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='6'){
                chars[i]='9';
                nums.add(Integer.valueOf(String.valueOf(chars)));
                chars[i]='6';
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='9'){
                chars[i]='6';
                nums.add(Integer.valueOf(String.valueOf(chars)));
                chars[i]='9';
            }
        }

       return nums.stream().distinct().max(Integer::compare).get();
    }
}