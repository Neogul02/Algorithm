package 수업;

public class CompleteBinaryTreeTest {
	
	public static void main(String[] args) {
		String[] names= {"박준우","안희수","차희광","김민준","이규섭","오양호","오태양","황우찬","최동준"};
		CompleteBinaryTree<String> tree = new CompleteBinaryTree<>(names.length);
		for(String n : names) {
			tree.add(n);
		}
//		tree.bfs();
//		tree.bfs2();
//		tree.bfs3();
		
		System.out.println("---dfs_preOrder---");
		tree.dfsByPreOrder(1);
		System.out.println("---dfs_inOrder---");
		tree.dfsByInOrder(1);
		System.out.println("---dfs_postOrder---");
		tree.dfsByPostOrder(1);
	}

}
