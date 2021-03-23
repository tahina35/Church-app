export interface Page<T> {

    pageNo: number
    totalPages: number
    totalItems: number
    content: T[]
}