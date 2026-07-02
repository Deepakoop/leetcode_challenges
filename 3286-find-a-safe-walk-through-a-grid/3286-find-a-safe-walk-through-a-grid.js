/**
 * @param {number[][]} grid
 * @param {number} health
 * @return {boolean}
 */
var findSafeWalk = function(grid, health) {
    const m = grid.length;
    const n = grid[0].length;

    // Max remaining health at each cell
    const best = Array.from({ length: m }, () => Array(n).fill(-1));

    // Health after entering the starting cell
    const startHealth = health - grid[0][0];
    if (startHealth <= 0) return false;

    // Max Heap
    class MaxHeap {
        constructor() {
            this.heap = [];
        }

        push(item) {
            this.heap.push(item);
            this._up(this.heap.length - 1);
        }

        pop() {
            if (this.heap.length === 1) return this.heap.pop();
            const top = this.heap[0];
            this.heap[0] = this.heap.pop();
            this._down(0);
            return top;
        }

        isEmpty() {
            return this.heap.length === 0;
        }

        _up(i) {
            while (i > 0) {
                const p = (i - 1) >> 1;
                if (this.heap[p][0] >= this.heap[i][0]) break;
                [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
                i = p;
            }
        }

        _down(i) {
            const n = this.heap.length;
            while (true) {
                let largest = i;
                let l = 2 * i + 1;
                let r = 2 * i + 2;

                if (l < n && this.heap[l][0] > this.heap[largest][0]) largest = l;
                if (r < n && this.heap[r][0] > this.heap[largest][0]) largest = r;

                if (largest === i) break;

                [this.heap[i], this.heap[largest]] = [this.heap[largest], this.heap[i]];
                i = largest;
            }
        }
    }

    const pq = new MaxHeap();
    pq.push([startHealth, 0, 0]);
    best[0][0] = startHealth;

    const dirs = [[1,0],[-1,0],[0,1],[0,-1]];

    while (!pq.isEmpty()) {
        const [curHealth, x, y] = pq.pop();

        if (x === m - 1 && y === n - 1) return true;

        if (curHealth < best[x][y]) continue;

        for (const [dx, dy] of dirs) {
            const nx = x + dx;
            const ny = y + dy;

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

            const nextHealth = curHealth - grid[nx][ny];

            if (nextHealth > 0 && nextHealth > best[nx][ny]) {
                best[nx][ny] = nextHealth;
                pq.push([nextHealth, nx, ny]);
            }
        }
    }

    return false;
};