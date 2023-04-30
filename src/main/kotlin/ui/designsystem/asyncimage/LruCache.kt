package ui.designsystem.asyncimage

class LruCache<K, V>(private val maxSize: Int) {
    private val map = LinkedHashMap<K, V>()

    fun get(key: K) = map[key]

    fun put(key: K, value: V) {
        if (map.size >= maxSize) {
            val iter = map.iterator()
            if (iter.hasNext()) {
                iter.remove()
            }
        }

        map[key] = value
    }
}
