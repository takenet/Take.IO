namespace TakeIoLib.Clients
{
    public interface IRestOperations<T>
    {
        Response<T> Get();
        Response<T> Post();
        Response<T> Put();
        Response<T> Delete();
    }
}