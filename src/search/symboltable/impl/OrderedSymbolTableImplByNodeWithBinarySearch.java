package search.symboltable.impl;

import search.symboltable.IOrderedSymbolTable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘建雯
 * 有序字符表：
 *        通过节点Node来实现,即节点，每个节点都有5个成员变量，分
 *        别为键key、值val、左节点指针left、右节点指针right、以
 *        及一该节点为根，其子树的大小size。该数据结构中，最为核
 *        心的方法，也是其他方法的基石技术就是二叉树的搜索，一切
 *        建立在对于二叉树搜索的递归上。
 *        几乎大部分方法都用到了内部递归工具方法，用来实现各种操
 *        作。但主要操作其实都是建立在二叉树搜索的基础上。
 *        这种有序字符表的实现优点是既用了二叉搜索，使其搜索的操
 *        作时间复杂度要比链表的顺序搜索要快不少，对于平衡树来说，
 *        其最坏情况只用N次比较，但由于数据的随机性，平均下来大
 *        概在1.39logN次，也达到了logN的水平，虽然比不上数组二
 *        分查找那么快，但是也不错。同时其还继承了像链表一样快速
 *        的插入，只不过需要进行一定的调整，不过比起数组的插入，
 *        还是快了不少，尤其不用像数组需要挪动数据。
 *        该数据结构的实现运用了很多的递归，有效解决了每个节点面
 *        临各种方法时，所需要做的各种操作。同时也为我学习递归开
 *        了个头。在分析递归时，要考虑三个问题：1.这个函数要干什
 *        么？ 2.结束条件？ 3.等价条件（或者简化到最小的操作）
 *        通过分析这么三个问题，有助于递归的实现。
 *  查找方式：
 *        二叉树查找
 */
@SuppressWarnings("unused")
public class OrderedSymbolTableImplByNodeWithBinarySearch<Key extends Comparable<Key>, Value> implements IOrderedSymbolTable<Key, Value> {
    private Node root;

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 辅助工具方法：插入
     *          三个问题：
     *          1.这个函数要干什么？
     *          插入一个节点，如果键key已经存在，则修改值val。
     *          2.结束条件？
     *          如果遍历到的节点为空，则创建一个新节点，并将键key以
     *          及值val输入其中，同时设置其子树大小size为1
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作）
     *          ，如果其键key小于要插入的键key，则对左节点调用相同的
     *          函数；如果大于，则对右节点调用相同的函数进入递归。
     * @param node  要进行操作的节点
     * @param key   要插入的键
     * @param val   要插入的值
     * @return      返回当前完成操作的node节点
     */
    private Node put(Node node, Key key, Value val){
        if(node == null){
            return new Node(key, val, 1);
        }
        int res = key.compareTo(node.key);
        if(res > 0){
            node.right = put(node.right, key, val);
        }else if(res < 0){
            node.left = put(node.left, key, val);
        }else{
            node.val = val;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        if(isEmpty()){
            return null;
        }
        return get(root, key);
    }

    /**
     * 辅助工具方法：查找，通过节点node和指定键key来查找其值val
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据键key来查找对应的值val，如果键key不存在，则返回null。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回null。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果其键key与指定的key相同，则返回当前节点的值val，如
     *          果小于，则对左节点调用相同的方法；如果大于，同理。
     * @param node  要进行操作的节点
     * @param key   查询的键key
     * @return      返回当前完成操作的node节点
     */
    private Value get(Node node, Key key){
        if(node == null){
            return null;
        }
        int res = node.key.compareTo(key);
        if(res == 0){
            return node.val;
        }
        return res < 0 ? get(node.right, key) : get(node.left, key);
    }

    @Override
    public void delete(Key key) {
        root = delete(root,key);
    }

    /**
     * 辅助工具方法：删除
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据键key来查找对应的值val，并删除该节点。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回null。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果其键key小于指定key，则对其左节点调用相同的方法，如
     *          果小于，同理。如果等于，则进行下面的操作：1)创建一个新
     *          的节点叫newNode，并使其指针指向当前的node。2)因为节点
     *          的右子树中所有元素的键key都是大于当前节点的，所以通过
     *          min()方法查找出右子树中最小的键，使当前node指针指向它。
     *          3)将newNode（即第1步中的原node）右子树进行删除最小节
     *          点操作，即调用deleteMin()方法，清除掉最小的键。4)使当
     *          前node右节点指向newNode清除了最小键的右子树。5)使node
     *          的左节点指向newNode的左子树。
     * @param node  要进行操作的节点
     * @param key   要删除的key
     * @return      返回当前完成操作的node节点
     */
    private Node delete(Node node, Key key){
        if(node == null){
            return null;
        }
        int res = key.compareTo(node.key);
        if(res < 0){
            node.left = delete(node.left,key);
        }else if(res > 0){
            node.right = delete(node.right,key);
        }else {
            if(node.left == null){
                return node.right;
            }
            if(node.right == null){
                return node.left;
            }
            Node newNode = node;
            node = min(node.right);
            node.right = deleteMin(newNode.right);
            node.left = newNode.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node){
        return node == null ? 0 : node.size;
    }

    @Override
    public Key min() {
        if(root == null){
            return null;
        }
        return min(root).key;
    }

    /**
     * 辅助工具方法：根据给定根节点查找最小键所在节点
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据给定根节点来查找当前树中最小键所在的节点。
     *          2.结束条件？
     *          如果遍历到的当前节点的左节点为空，则返回当前节点。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          除非其没有左节点，否则对于该节点的子树中最小键所在的节
     *          点一定在左子树中。
     *          所以对于该节点进行判断，如果有左节点，则对左节点调用该
     *          方法，如果左节点不存在，则对右节点调用该方法。
     * @param node  要进行操作的节点
     * @return      查找到最小键所在的节点
     */
    private Node min(Node node){
        if(node.left == null){
            return node;
        }else {
            return min(node.left);
        }
    }

    @Override
    public Key max() {
        if(root == null){
            return null;
        }
        return max(root).key;
    }

    /**
     * 辅助工具方法：根据给定根节点查找最大键所在节点，其原理与min()相似。
     *              只不过对于一个节点，除非不存在右节点，否则其子树中最
     *              大键所在的节点一定在右子树中。
     * @param node  要进行操作的节点
     * @return      查找到最大键所在的节点
     */
    private Node max(Node node){
        if(node.right == null){
            return node;
        }else {
            return max(node.right);
        }
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(root,key);
        return node == null ? null : node.key;
    }

    /**
     * 辅助工具方法：向下取整，允许指定的键key不存在
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据给定根节点以及键，查找出小于该键key的最大节点。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回当前节点。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果其key大于给定的key，则对其左节点调用相同的方法进行
     *          递归。如果大于，则对其右节点调用相同的方法，并该方法的
     *          返回值进行判断，如果为null，则表明给定的key大于当前节
     *          点，并小于其右子树中所有元素的key，那么返回当前节点；
     *          否则返回右节点调用方法的返回值。
     * @param node  要进行操作的节点
     * @param key   指定的键key
     * @return      返回完成操作的node节点
     */
    private Node floor(Node node, Key key){
        if(node == null){
            return null;
        }
        int res = key.compareTo(node.key);
        if(res < 0){
            return floor(node.left, key);
        }
        Node newNode = floor(node.right,key);
        return newNode == null ? node : newNode;
    }

    @Override
    public Key ceiling(Key key) {
        Node node = ceiling(root,key);
        return node == null ? null : node.key;
    }

    /**
     * 辅助工具方法：向上取整，允许指定的键key不存在
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据给定根节点以及键，查找出大于该键key的最小节点。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回当前节点。
     *          3.等价条件
     *          与上面的floor（向下取整）方法原理一样，左右对调即
     *          可。
     * @param node  要进行操作的节点
     * @param key   指定的键key
     * @return      返回完成操作的node节点
     */
    private Node ceiling(Node node, Key key){
        if(node == null){
            return null;
        }
        int res = key.compareTo(node.key);
        if(res > 0){
            return ceiling(node.right,key);
        }
        Node newNode = ceiling(node.left,key);
        return newNode == null ? node : newNode;
    }

    @Override
    public int rank(Key key) {
        return rank(root,key);
    }

    /**
     * 辅助工具方法：获取指定key的排序位置，允许key不存在
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据给定根节点以及键，找出其排序位置。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回0。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果当前节点的键key大于指定key，则对其左节点调用相同方
     *          法进行递归，并返回方法得到的int类型的值。如果小于，则对
     *          右节点调用相同方法，并返回该方法得到的值加左节点的size+1。
     *          ps：加1是因为还要加上该节点自身
     *          如果等于，则返回其左子树的大小，即左节点的size。
     * @param node  要进行操作的节点
     * @param key   指定的键
     * @return      当前完成操作的node节点
     */
    private int rank(Node node, Key key){
        if(node == null){
            return 0;
        }
        int res = key.compareTo(node.key);
        if(res < 0){
            return rank(node.left,key);
        }else if(res > 0){
            return node.left.size + 1 + rank(node.right,key);
        }else {
            return node.left.size;
        }
    }

    @Override
    public Key select(int k) {
        return select(root,k).key;
    }

    /**
     * 辅助工具方法：获取指定key的排序位置，允许key不存在
     *          三个问题：
     *          1.这个函数要干什么？
     *          根据给定根节点以及键，找出其排序位置。
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回0。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果当前节点的键size大于指定的k，则对左节点调用相同方法
     *          递归；如果小于，则对右节点调用相同方法，同时参数k变为
     *          k-1-左节点的size；如果相等，则说明该节点就是排序位置为
     *          k的节点，返回当前node节点。
     * @param node  要进行操作的节点
     * @param k     指定的排序
     * @return      指定排序位置的节点的键key
     */
    private Node select(Node node, int k){
        if(node == null){
            return null;
        }
        int size = size(node.left);
        if(size > k){
            return select(node.left,k);
        }else if(size < k){
            return select(node.right,k-size-1);
        }else {
            return node;
        }
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 辅助工具方法：删除最小键所在的节点。
     *          三个问题：
     *          1.这个函数要干什么？
     *          删除最小键所在的节点。
     *          2.结束条件？
     *          如果遍历到的当前节点的左节点为空，则说明该节点为最小键
     *          所在的节点，直接返回右节点。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果其有左节点，则此节点必定不是最小键所在节点，继续对
     *          左节点调用相同方法，同时更新节点的size信息，直到遇到结
     *          束条件
     * @param node  要进行操作的节点
     * @return      当前完成操作的node节点
     */
    private Node deleteMin(Node node){
        if(node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    /**
     * 辅助工具方法：删除最大键所在的节点。原理与上面删除最小节点方法类似，
     *              只需左右调换即可。
     * @param node  要进行操作的节点
     * @return      当前完成操作的node节点
     */
    private Node deleteMax(Node node){
        if(node.right == null){
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.size = size(node.right) + size(node.left) + 1;
        return node;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> keyList = new ArrayList<>();
        keys(root, keyList, lo, hi);
        return keyList;
    }

    /**
     * 辅助工具方法：获取指定范围内的键key
     *          三个问题：
     *          1.这个函数要干什么？
     *          给定key的范围，获取容器中符合条件的所有key
     *          2.结束条件？
     *          如果遍历到的当前节点为空，则返回null。
     *          3.等价条件
     *          对于一个节点（确保存在，如果不存在则按照结束条件操作），
     *          如果该节点的键key大于下界键lo，则说明其左子树中还有key
     *          符合条件，对左节点调用相同方法。如果大于上界键hi，则对
     *          右节点调用相同方法。如果即大于等于lo，又小于等于hi，表
     *          明该节点的键key也符合条件，加入到keyList容器中。
     * @param node      要进行操作的节点
     * @param keyList   容器，用于记录符合条件的key
     * @param lo        指定范围的上界
     * @param hi        指定范围的下界
     */
    private void keys(Node node, List<Key> keyList, Key lo, Key hi){
        if(node == null){
            return;
        }
        int resLow = lo.compareTo(node.key);
        int resHigh = hi.compareTo(node.key);
        if(resLow < 0){
            keys(node.left, keyList, lo, hi);
        }
        if(resLow <= 0 && resHigh >= 0){
            keyList.add(node.key);
        }
        if(resHigh > 0){
            keys(node.right, keyList, lo, hi);
        }
    }

    private class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int size;

        public Node(){

        }

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
}


