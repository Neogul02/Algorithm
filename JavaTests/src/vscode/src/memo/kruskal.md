## KRUSKAL ALGORITHM
### 최소 신장 트리(Minimum Spanning Tree, MST)

- 그래프를 만들때 간선에서의 가중치(비용)이 있는 그래프가 있으면
- 최소 비용으로 모든 정점을 연결하는 트리 자료구조를 만들 수 있다.

- 최소 비용으로 모든 정점을 연결하는 트리를 **최소 신장 트리** 라고 한다.

- 사이클이 없어야 함, 간선의 수는 노드의 수 - 1개여야 함.
- 

그리디 알고리즘??

```java
// 예시

static int[] parentArray;
static int[] rankArray;

public static void make() {
    for(int elementIndex = 0; elementIndex < ELEMENT_COUNT; elementIndex++) {
        parentArray[elementIndex] = elementIndex; // 나 자신을 부모로 설정.
        rankArray[elementIndex] = 0; // 나 자신의 우선순위 설정. (최초 동일 우선순위)
    }
}

public static int find(int element) {

    // 나 자신이 부모라면 더 이상 부모가 없다.
    if(parentArray[element] == element) {
        return element;
    }

    // 경로 압축
    // 부모를 찾아왔으면 바로 갱신해준다.
    return parentArray[element] = find(parrentArray[element]);

    // 나 자신이 부모가 아니라면 아직 부모를 찾아가야한다.
    return find(parentArray[element]); // 내 부모를 넘겨주고 그 부모를 찾아감.
}

public static void union(int element1, int element2) {

    // 두 집합을 하나로 합쳐줄 때에는 반드시 부모(대장)이 나와야한다.
    int element1Parent = find(element1);
    int element2Parent = find(element2);

    // 두 집합의 부모를 찾아왔더니 부모가 같으면 동일한 집합에 속해있는 것이다.
    if(element1Parent == element2Parent) {
        return;
    }

    // 서로 부모가 다르다면 누가 더 대장인지 판단해준다.

    // element1Parent가 더 랭크가 높으면
    if(rankArray[element1Parent] > rankArray[element2Parent]) {
        // element2Parent는 element1Parent의 자식이 된다.
        parentArray[element2Parent] = element1Parent;
        return;
    }

    // element1Parent와 element2Parent의 랭크가 같거나 작으면,
    // element2Parent가 부모가 되도록 해주자. --> element2Parent의 랭크를 높혀준다.
    if(rankArray[element1Parent] == rankArray[element2Parent]) {
        rankArray[element2Parent]++;
    }

    parentArray[element1Parent] = element2Parent;

}

static final int NODE_COUNT = 7;
static final int EDGE_COUNT = 9;
public static void main(String[] args) {

    make(); // 초기화

    edgeSort(); // 정렬을 하는데, 모든 간선들을 가중치를 기준으로 오름차순 정렬을 함.
    // 하나의 배열(또는 리스트)를 정렬해도 되고, PriorityQueue를 사용해도 된다.

    int sumWeight = 0;

    for(Edge edge : sortedEdgeList) { // 간선의 수 만큼 반복

        int from = edge.from;
        int to = edge.to;
        int weight = edge.weight;

        // 두 노드의 부모가 동일하면 사이클이 형성된다.
        if(find(from) == find(to)) {
            continue;
        }

        // 두 노드의 부모가 다르면 가중치를 합산.
        sumWegith += weight;
    }
}

```
