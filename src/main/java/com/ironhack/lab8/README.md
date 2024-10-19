## API Route Design

### Did you use the same type of route to update patient information as to update an employee's department?
Yes, I used `PUT` routes for both updating patient information and updating an employee's department.

### Why did you choose the selected strategy?
I chose `PUT` because it is ideal for updating an entire resource, ensuring that all relevant fields are updated simultaneously.

### What are the advantages and disadvantages of the strategies you chose for creating these routes?
**Advantages**:
- `PUT` provides a clear and consistent method for updating resources, ensuring that clients always receive a full representation of the updated resource.

**Disadvantages**:
- It requires sending all fields, which can lead to inefficiencies if only a few fields need to be updated.

### What is the cost-benefit between using PUT and PATCH?
`PUT` replaces the entire resource, which can be costly in terms of data transfer if only minor changes are made. `PATCH`, on the other hand, allows for partial updates, making it more efficient for small changes. However, `PATCH` can be less straightforward in terms of implementation and understanding by clients.
